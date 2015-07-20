package de.dengpeng.projects;


/**
 * The Class Report.
 * This class is the return object of AWS Lambda function.
 */
public class Report {
	
	/** The status. */
	private boolean status = false;
	
	/** The file size. */
	private long fileSize;
	
	/** The execution time. */
	private long executionTime;
	
	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public long getFileSize() {
		return fileSize;
	}
	
	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	/**
	 * Gets the execution time.
	 *
	 * @return the execution time
	 */
	public long getExecutionTime() {
		return executionTime;
	}
	
	/**
	 * Sets the execution time.
	 *
	 * @param executiongTime the new execution time
	 */
	public void setExecutiongTime(long executiongTime) {
		this.executionTime = executiongTime;
	}
}
