<?xml version="1.0" encoding="UTF-8" ?>
 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
	        
	<xsl:template match="/carTypes">
            <table class="c_table b_table">
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
                <tbody>
                        <tr>
                            <th><div class="th_div b_div">ID</div></th>
                            <th><div class="th_div b_div">Название</div></th>
                            <th><div class="th_div b_div">Вместимость</div></th>
                            <th><div class="th_div b_div">Стоимость за 1 км</div></th>
                            <th><div class="b_div">Имеющиеся автомобили</div></th>
                        </tr>
                        
                        <xsl:apply-templates/>
                        
                </tbody>
            </table>
	</xsl:template>
	
	<xsl:template match="/carTypes/carType">
		<tr>
                    <td><xsl:value-of select="@id"/></td>
                    <td><xsl:value-of select="@name"/></td>
                    <td><xsl:value-of select="@capacity"/></td>
                    <td><xsl:value-of select="@costPerKm"/></td>
                    <xsl:apply-templates/>
                </tr>
	</xsl:template>
	
	<xsl:template match="/carTypes/carType/cars">
            <td>
                <xsl:for-each select="car">
                    <xsl:value-of select="@model"/><br/>
                </xsl:for-each>
            </td>
	</xsl:template>
	
</xsl:stylesheet>