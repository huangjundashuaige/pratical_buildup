import org.junit.Test;
import static org.junit.Assert.*;
public class calculate_test {
	Calculator calculation = new Calculator();
	int sum = calculation.test();
	int test_sum=4;
	@Test
	public void test()
	{
		System.out.println(sum+" "+test_sum);
		assertEquals(sum,test_sum);
	}
}
