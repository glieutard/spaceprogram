/**
 * 
 */
package com.spaceprogram.model.coordinates;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 * 
 * Virtual Entity Coordinates
 *
 */
@Embeddable
@ApiObject(name = "Coordinates", description = "Virtual Entity Coordinates", show = false)
public class Coordinates implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -9166888540675015213L;
	
	/**
	 * Coordinate X
	 */
	@ApiObjectField(description = "Coordinate X")
	private BigDecimal x;
	
	/**
	 * Coordinate Y
	 */
	@ApiObjectField(description = "Coordinate Y")
	private BigDecimal y;
	
	/**
	 * Coordinate Z
	 */
	@ApiObjectField(description = "Coordinate Z")
	private BigDecimal z;

	/**
	 * @return the x
	 */
	public BigDecimal getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(BigDecimal x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public BigDecimal getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(BigDecimal y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public BigDecimal getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(BigDecimal z) {
		this.z = z;
	}

}
