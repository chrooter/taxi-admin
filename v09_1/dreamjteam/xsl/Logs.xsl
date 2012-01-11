<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Logs.xsl
    Created on : 17 Декабрь 2011 г., 19:13
    Author     : диман
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    	<xsl:template match="/logs">
            <table class="c_table b_table">
                
                <col width="30%"/>
                <col width="30%"/>
                
                <col width="40%"/>
                <tbody>
                        <tr>
                            
                            <th><div class="th_div b_div">Сущность</div></th>
                            <th><div class="th_div b_div">Дата изменения</div></th>
                            
                            <th><div class="th_div b_div">Изменение</div></th>
                        </tr>

                        <xsl:apply-templates/>

                </tbody>
            </table>
	</xsl:template>

	<xsl:template match="/logs/log">
		<tr>
                    
                    <td><xsl:value-of select="@entity"/></td>
                    <td><xsl:value-of select="@dateOfChange"/></td>
                    
                    <td><xsl:value-of select="@change"/></td>
                    <xsl:apply-templates/>
                </tr>
	</xsl:template>

</xsl:stylesheet>
