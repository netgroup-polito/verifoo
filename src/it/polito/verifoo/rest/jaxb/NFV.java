//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.24 at 10:08:11 AM CET 
//


package it.polito.verifoo.rest.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}graphs"/>
 *         &lt;element ref="{}Constraints" minOccurs="0"/>
 *         &lt;element ref="{}PropertyDefinition"/>
 *         &lt;element ref="{}Hosts" minOccurs="0"/>
 *         &lt;element ref="{}Connections" minOccurs="0"/>
 *         &lt;element ref="{}NetworkForwardingPaths" minOccurs="0"/>
 *         &lt;element name="ParsingString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "graphs",
    "constraints",
    "propertyDefinition",
    "hosts",
    "connections",
    "networkForwardingPaths",
    "parsingString"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(required = true)
    protected Graphs graphs;
    @XmlElement(name = "Constraints")
    protected Constraints constraints;
    @XmlElement(name = "PropertyDefinition", required = true)
    protected PropertyDefinition propertyDefinition;
    @XmlElement(name = "Hosts")
    protected Hosts hosts;
    @XmlElement(name = "Connections")
    protected Connections connections;
    @XmlElement(name = "NetworkForwardingPaths")
    protected NetworkForwardingPaths networkForwardingPaths;
    @XmlElement(name = "ParsingString")
    protected String parsingString;

    /**
     * Gets the value of the graphs property.
     * 
     * @return
     *     possible object is
     *     {@link Graphs }
     *     
     */
    public Graphs getGraphs() {
        return graphs;
    }

    /**
     * Sets the value of the graphs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Graphs }
     *     
     */
    public void setGraphs(Graphs value) {
        this.graphs = value;
    }

    /**
     * Gets the value of the constraints property.
     * 
     * @return
     *     possible object is
     *     {@link Constraints }
     *     
     */
    public Constraints getConstraints() {
        return constraints;
    }

    /**
     * Sets the value of the constraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Constraints }
     *     
     */
    public void setConstraints(Constraints value) {
        this.constraints = value;
    }

    /**
     * Gets the value of the propertyDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyDefinition }
     *     
     */
    public PropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

    /**
     * Sets the value of the propertyDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyDefinition }
     *     
     */
    public void setPropertyDefinition(PropertyDefinition value) {
        this.propertyDefinition = value;
    }

    /**
     * Gets the value of the hosts property.
     * 
     * @return
     *     possible object is
     *     {@link Hosts }
     *     
     */
    public Hosts getHosts() {
        return hosts;
    }

    /**
     * Sets the value of the hosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hosts }
     *     
     */
    public void setHosts(Hosts value) {
        this.hosts = value;
    }

    /**
     * Gets the value of the connections property.
     * 
     * @return
     *     possible object is
     *     {@link Connections }
     *     
     */
    public Connections getConnections() {
        return connections;
    }

    /**
     * Sets the value of the connections property.
     * 
     * @param value
     *     allowed object is
     *     {@link Connections }
     *     
     */
    public void setConnections(Connections value) {
        this.connections = value;
    }

    /**
     * Gets the value of the networkForwardingPaths property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkForwardingPaths }
     *     
     */
    public NetworkForwardingPaths getNetworkForwardingPaths() {
        return networkForwardingPaths;
    }

    /**
     * Sets the value of the networkForwardingPaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkForwardingPaths }
     *     
     */
    public void setNetworkForwardingPaths(NetworkForwardingPaths value) {
        this.networkForwardingPaths = value;
    }

    /**
     * Gets the value of the parsingString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParsingString() {
        return parsingString;
    }

    /**
     * Sets the value of the parsingString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParsingString(String value) {
        this.parsingString = value;
    }

}
