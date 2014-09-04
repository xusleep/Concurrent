package zhonglin.test.testcase;

public class WorkEntityResult implements WorkEntityResultInterface {
	
	private boolean m_isSuccessFul;
	private Exception m_Exception;
	private String m_description;
	
	public WorkEntityResult()
	{
		
	}
	
	public WorkEntityResult(boolean isSuccessFul, String description, Exception exception)
	{
		m_description = description;
		m_isSuccessFul = isSuccessFul;
		m_Exception = exception;
	}

	@Override
	public boolean isSucessful() {
		// TODO Auto-generated method stub
		return m_isSuccessFul;
	}

	public void setIsSuccessFul(boolean m_isSuccessFul) {
		this.m_isSuccessFul = m_isSuccessFul;
	}
	
	@Override
	public Exception getException() {
		return m_Exception;
	}

	public void setException(Exception m_Exception) {
		this.m_Exception = m_Exception;
	}

	@Override
	public String getDescription() {
		return m_description;
	}

	public void setDescription(String m_description) {
		this.m_description = m_description;
	}

}
