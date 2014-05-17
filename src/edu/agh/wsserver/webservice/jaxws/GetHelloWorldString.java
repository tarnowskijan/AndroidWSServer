package edu.agh.wsserver.webservice.jaxws;

import jdk.javax.xml.bind.annotation.XmlAccessType;
import jdk.javax.xml.bind.annotation.XmlAccessorType;
import jdk.javax.xml.bind.annotation.XmlElement;
import jdk.javax.xml.bind.annotation.XmlRootElement;
import jdk.javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getHelloWorldString", namespace = "http://webservice.wsserver.agh.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHelloWorldString", namespace = "http://webservice.wsserver.agh.edu/")
public class GetHelloWorldString {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

}
