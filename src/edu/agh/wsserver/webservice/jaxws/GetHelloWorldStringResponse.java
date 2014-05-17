package edu.agh.wsserver.webservice.jaxws;

import jdk.javax.xml.bind.annotation.XmlAccessType;
import jdk.javax.xml.bind.annotation.XmlAccessorType;
import jdk.javax.xml.bind.annotation.XmlElement;
import jdk.javax.xml.bind.annotation.XmlRootElement;
import jdk.javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getHelloWorldStringResponse", namespace = "http://webservice.wsserver.agh.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHelloWorldStringResponse", namespace = "http://webservice.wsserver.agh.edu/")
public class GetHelloWorldStringResponse {

    @XmlElement(name = "return", namespace = "")
    private String _return;

    /**
     * 
     * @return
     *     returns String
     */
    public String getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(String _return) {
        this._return = _return;
    }

}
