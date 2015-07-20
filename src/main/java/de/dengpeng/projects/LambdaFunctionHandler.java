package de.dengpeng.projects;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.common.collect.Lists;

import au.com.bytecode.opencsv.CSVReader;


/**
 * The Class LambdaFunctionHandler.
 * This application loads GZIPped CSV file to DynamoDB using AWS Lambda function.
 * 
 */
public class LambdaFunctionHandler implements RequestHandler<S3Event, Report> {
	
	/** Provide the AWS region which your DynamoDB table is hosted. */
	Region AWS_REGION = Region.getRegion(Regions.US_WEST_2);

	/** The DynamoDB table name. */
	String DYNAMO_TABLE_NAME = "PROVIDE_DYNAMODB_TABLE_NAME_HERE";

	/* (non-Javadoc)
	 * @see com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java.lang.Object, com.amazonaws.services.lambda.runtime.Context)
	 */
	public Report handleRequest(S3Event s3event, Context context) {
		long startTime = System.currentTimeMillis();
		Report statusReport = new Report();
		LambdaLogger logger = context.getLogger();

		logger.log("Lambda Function Started");
		Helper helper = new Helper();

		try {
			S3EventNotificationRecord record = s3event.getRecords().get(0);
			String srcBucket = record.getS3().getBucket().getName();
			// Object key may have spaces or unicode non-ASCII characters.
			String srcKey = record.getS3().getObject().getKey().replace('+', ' ');
			srcKey = URLDecoder.decode(srcKey, "UTF-8");

			AmazonS3 s3Client = new AmazonS3Client();
			S3Object s3Object = s3Client.getObject(new GetObjectRequest(srcBucket, srcKey));
			statusReport.setFileSize(s3Object.getObjectMetadata().getContentLength());

			logger.log("S3 Event Received: " + srcBucket + "/" + srcKey);

			// Read zip file directly from S3
			// Future we may need to add security features and increase throughput by multi-thread
			GZIPInputStream gis = new GZIPInputStream(s3Object.getObjectContent());
			InputStreamReader isr = new InputStreamReader(gis);
			BufferedReader br = new BufferedReader(isr);
			CSVReader reader = new CSVReader(br);

			AmazonDynamoDB dynamoDBClient = new AmazonDynamoDBClient();
			dynamoDBClient.setRegion(AWS_REGION);
			DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);

			TableWriteItems energyDataTableWriteItems = new TableWriteItems(DYNAMO_TABLE_NAME);

			List<Item> itemList = new ArrayList<Item>();

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				// logger.log(nextLine[0] + "," + nextLine[1] + ", etc...");

				Item newItem = helper.parseIt(nextLine);

				itemList.add(newItem);
			}

			for (List<Item> partition : Lists.partition(itemList, 25)) {
				energyDataTableWriteItems.withItemsToPut(partition);
				BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(energyDataTableWriteItems);

				do {

					// Check for unprocessed keys which could happen if you
					// exceed provisioned throughput

					Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();

					if (outcome.getUnprocessedItems().size() > 0) {
						logger.log("Retrieving the unprocessed " + String.valueOf(outcome.getUnprocessedItems().size())
								+ " items.");
						outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
					}

				} while (outcome.getUnprocessedItems().size() > 0);

			}

			logger.log("Load finish in " + String.valueOf(System.currentTimeMillis() - startTime) + "ms");

			reader.close();
			br.close();
			isr.close();
			gis.close();
			s3Object.close();

			statusReport.setStatus(true);
		} catch (Exception ex) {
			logger.log(ex.getMessage());
		}

		statusReport.setExecutiongTime(System.currentTimeMillis() - startTime);
		return statusReport;
	}

}
