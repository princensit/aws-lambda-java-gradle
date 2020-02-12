<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
  <xsl:template match="/">
    <xsl:element name="root">
      <xsl:for-each select="catalog/cd">
        <xsl:element name="row">
          <xsl:element name="title">
            <xsl:value-of select="title"/>
          </xsl:element>
          <xsl:element name="artist">
            <xsl:value-of select="artist"/>
          </xsl:element>
        </xsl:element>
      </xsl:for-each>
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
