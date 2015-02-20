package ut.com.anthem.juju;

import org.junit.Test;
import com.anthem.juju.MyPluginComponent;
import com.anthem.juju.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}