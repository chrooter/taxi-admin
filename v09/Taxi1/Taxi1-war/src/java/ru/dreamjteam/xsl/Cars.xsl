<?xml version="1.0" encoding="UTF-8" ?>
 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
	        
	<xsl:template match="/cars">
            <table class="c_table b_table">
                <col width="10%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>

                <tbody>
                    <tr>
                        <th><div class="th_div b_div">ID</div></th>
                        <th><div class="th_div b_div">Модель</div></th>
                        <th><div class="th_div b_div">Гос. Номер</div></th>
                        <th><div class="th_div b_div">Цвет</div></th>
                        <th><div class="th_div b_div">Тип</div></th>
                        <th><div class="th_div b_div">Заказы</div></th>
                        <th><div class="b_div">Активные заказы</div></th>
                    </tr>
                        
                        <xsl:apply-templates/>
                        
                </tbody>
            </table>
	</xsl:template>
	
	<xsl:template match="/cars/car">
            <tr>
                <td><xsl:value-of select="@id"/></td>
                <td><xsl:value-of select="@model"/></td>
                <td><xsl:value-of select="@govNumber"/></td>
                <td><xsl:value-of select="@color"/></td>
                <td><xsl:value-of select="@carTypeId"/></td>
                <xsl:apply-templates/>
            </tr>
	</xsl:template>
	
	<xsl:template match="/cars/car/orders">
            <td>
                <xsl:for-each select="order">
                    <xsl:value-of select="@id"/><br/>
                </xsl:for-each>
            </td>
	</xsl:template>
        
        <xsl:template match="/cars/car/currentOrders">
            <td>
                <xsl:for-each select="order">
                    <xsl:value-of select="@id"/><br/>
                </xsl:for-each>
            </td>
	</xsl:template>
	
</xsl:stylesheet>