package nl.javadude.monopoly.resources;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.common.collect.*;

public class DummyHttpSession implements HttpSession
{
    private Map<String, Object> theMap = Maps.newHashMap();

    public long getCreationTime()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getId()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getLastAccessedTime()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ServletContext getServletContext()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMaxInactiveInterval(int i)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getMaxInactiveInterval()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HttpSessionContext getSessionContext()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getAttribute(String s)
    {
        return theMap.get(s);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getValue(String s)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Enumeration getAttributeNames()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String[] getValueNames()
    {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAttribute(String s, Object o)
    {
        this.theMap.put(s, o);
    }

    public void putValue(String s, Object o)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeAttribute(String s)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeValue(String s)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void invalidate()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNew()
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

