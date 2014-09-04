package zhonglin.test.chapter5.container.vector;

import java.util.Random;
import java.util.Vector;

import zhonglin.test.chapter5.container.ContainerOperator;
import zhonglin.test.testcase.CheckWorkEntityResultInterface;
import zhonglin.test.testcase.WorkEntityResult;
import zhonglin.test.testcase.WorkEntityResultInterface;

/**
 *  此测试 内部并没有使用迭代器访问，但是并没有同步出错，但是却因为出现了index 越界错误，是因为在访问容器vector的时候
 *  进行了修改操作。
 * @author Smile
 *
 */
public class VectorOperatorNotIterator implements ContainerOperator, CheckWorkEntityResultInterface {
	
	private Vector vector = new Vector();
	private WorkEntityResult objWorkEntityResult = new WorkEntityResult();
	
	public VectorOperatorNotIterator()
	{
		for(int i = 0; i < 1000; i++)
			vector.add(i);
	}

	@Override
	public void interatorTheVector() {
		try
		{
			// TODO Auto-generated method stub
			System.out.println("runing ");
			String s = "";
			int length = vector.size();
			for(int i = 0; i < length; i++)
			{
				int j = (int)vector.get(i);
				s = s + "i = " + i + " value " + j + "\r\n";
			}
			System.out.println(s);
			objWorkEntityResult.setIsSuccessFul(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objWorkEntityResult.setIsSuccessFul(false);
			objWorkEntityResult.setException(e);
			objWorkEntityResult.setDescription("the exception comes out, mostly it is arrayindexoutof range error, concurrent issue");
		}
	}

	@Override
	public void modifyTheVector() {
		try
		{
			// TODO Auto-generated method stub
			Random random = new Random();
			int i = random.nextInt(vector.size());
			System.out.println("remove index " + i);
			vector.remove(i);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objWorkEntityResult.setIsSuccessFul(false);
			objWorkEntityResult.setException(e);
			objWorkEntityResult.setDescription("the exception comes out, mostly it is arrayindexoutof range error, concurrent issue");
		}
	}

	@Override
	public WorkEntityResultInterface checkResult() {
		// TODO Auto-generated method stub
		return objWorkEntityResult;
	}


}
