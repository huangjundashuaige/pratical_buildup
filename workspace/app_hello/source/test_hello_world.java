import static org.junit.Assert.*;
import org.junit.Test;
public class test_hello_world
{
	String s=hello_world.output();
	String s1="hello world!\n";
	@Test
	public void test()
	{
		System.out.println("expected: "+s1+"\n"+"actual: "+s);
		assertEquals(s, s1);
	}
}