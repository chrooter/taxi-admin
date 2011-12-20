<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Orders.xsl
    Created on : 20 Декабрь 2011 г., 9:57
    Author     : newworker
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

<xsl:template match="/orders">
            <table class="c_table b_table">
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <tbody>
                        <tr>
                            <th><div class="th_div b_div">ID</div></th>
                            <th><div class="th_div b_div">Созданна</div></th>
                            <th><div class="th_div b_div">Выполненна</div></th>
                            <th><div class="th_div b_div">Пункт отправления</div></th>
                            <th><div class="th_div b_div">Пассажиры</div></th>
                            <th><div class="th_div b_div">Дистанция</div></th>
                            <th><div class="th_div b_div">Стоимость</div></th>
                            <th><div class="th_div b_div">Телефон</div></th>
                            <th><div class="th_div b_div">Статус</div></th>
                            <th><div class="b_div">Автомобиль</div></th>
                        </tr>

                        <xsl:apply-templates/>

                </tbody>
            </table>
	</xsl:template>

        <xsl:template match="/orders/order">
		<tr>
                    <td><xsl:value-of select="@id"/></td>
                    <td><xsl:value-of select="@timeOrd"/></td>
                    <td><xsl:value-of select="@timeDone"/></td>
                    <td><xsl:value-of select="@startPoint"/></td>
                    <td><xsl:value-of select="@passengers"/></td>
                    <td><xsl:value-of select="@distance"/></td>
                    <td><xsl:value-of select="@cost"/></td>
                    <td><xsl:value-of select="@phone"/></td>
                    <td><xsl:value-of select="@status"/></td>
                    <xsl:apply-templates/>
                </tr>
	</xsl:template>

        <xsl:template match="/orders/order/car">
            <td>              
                    <xsl:value-of select="@model"/><br/>
            </td>
	</xsl:template>

</xsl:stylesheet>
