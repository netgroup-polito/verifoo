//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.15 at 03:28:19 PM CET 
//


package it.polito.verifoo.rest.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Property complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Property">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{}P-Name" />
 *       &lt;attribute name="isSat" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="graph" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Property")
public class Property {

    @XmlAttribute(name = "name", required = true)
    protected PName name;
    @XmlAttribute(name = "isSat")
    protected Boolean isSat;
    @XmlAttribute(name = "graph", required = true)
    protected long graph;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link PName }
     *     
     */
    public PName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link PName }
     *     
     */
    public void setName(PName value) {
        this.name = value;
    }

    /**
     * Gets the value of the isSat property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSat() {
        return isSat;
    }

    /**
     * Sets the value of the isSat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSat(Boolean value) {
        this.isSat = value;
    }

    /**
     * Gets the value of the graph property.
     * 
     */
    public long getGraph() {
        return graph;
    }

    /**
     * Sets the value of the graph property.
     * 
     */
    public void setGraph(long value) {
        this.graph = value;
    }

}
