<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="no" encoding="ISO-8859-1"/>
	<xsl:template match="/Base">
		<html>
			<head>
				<title>Mesure project | Output</title>
			</head>
			<body>	
			
				<xsl:if test="@lang = 'fr'">
					<xsl:call-template name="french_body">                       	
      				</xsl:call-template> 
      			</xsl:if>
      			
				<xsl:if test="@lang = 'en'">
					<xsl:call-template name="english_body">                       	
      				</xsl:call-template> 
      			</xsl:if>
		
			</body>
		</html>
</xsl:template>


<xsl:template name="english_body">

				<h1>Evaluation <xsl:value-of select="Bench/Test/@date"/></h1>
                <p>
                    ATR : <xsl:value-of select="Bench/@atr"/><br />
                    CAD : <xsl:value-of select="managerConfig/cad"/><br />
                    Host : <xsl:value-of select="Environnement/@host"/><br />
                    VM Java : <xsl:value-of select="Environnement/@VMJava"/><br />
                    Benchmark Version : (version svn)<br />
                    Total execution time : (time)<br />
                </p>
                <br />

			    <h1>Trust index</h1>
                <p>
                    Filter : (filter index)<br />
                    Precision : (precision index)<br />
                    
                </p>
                <br />

				<h1>Global Result</h1>
                <p>
                    General mark : <xsl:value-of select="Results/@mark"/><br /><br />
                    
                    <img src="charts/notes_chart.png"/><br />
                    <i>Synthetic graph per domain</i><br />
                    <br />
                    Transport mark : <xsl:value-of select="Results/Domain[position()=1]/@mark"/><br />
                    Financial mark : <xsl:value-of select="Results/Domain[position()=3]/@mark"/><br />
                    Identity mark : <xsl:value-of select="Results/Domain[position()=2]/@mark"/><br />
                </p>
                <br />

				<h1>Detailed Results</h1>
                <p>
                    <h2>Transport</h2>
                        <h3>APIs</h3>

                    List of the most used methods (descending order) in the &lt;&lt;Transport&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    <table border="1">
                   	<tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(ns)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">1</xsl:with-param>
                        	</xsl:call-template>
                        
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_transport.png"/> </td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>
                    <br /><br />

                        <h3>VM</h3>

                    List of the most used bytecodes (descending order) in the &lt;&lt;Transport&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(ns)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">1</xsl:with-param>
                        	</xsl:call-template>
                            
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr> <td><img src="charts/chart_vm_transport.png"/> </td> </tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>    
                    <br /><br /><br />

                    <h2>Financial</h2>
                        <h3>APIs</h3>

                    List of the most used methods (descending order) in the &lt;&lt;Financial&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(ns)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">3</xsl:with-param>
                        	</xsl:call-template>
                        
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_bancaire.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />

                        <h3>VM</h3>

       				List of the most used bytecodes (descending order) in the &lt;&lt;Financial&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>                    
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(ns)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">3</xsl:with-param>
                        	</xsl:call-template>
                                           
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_vm_bancaire.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br /><br />

                    <h2>Identity</h2>
                        <h3>APIs</h3>

                    List of the most used methods (descending order) in the &lt;&lt;Identity&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>                    
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(&#181;s)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">2</xsl:with-param>
                        	</xsl:call-template>  
                      
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_identite.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />

                        <h3>VM</h3>

					List of the most used bytecodes (descending order) in the &lt;&lt;Identity&gt;&gt; market. Only the ten most significative operations are shown below, for more details, please go to the <a href="#ResultatBrut">Raw results section</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Name</th>
                            <th>Average time(ns)</th>
                            <th>Impact Coef.</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">2</xsl:with-param>
                        	</xsl:call-template>  
                      
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Chart</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_vm_identite.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />
                </p>

                <a name="ResultatBrut"><h1>Raw results</h1></a>
                    <h2>Methods execution time</h2>
                        <table border="1" cellpadding="5">
                        	<tr bgcolor="#FFCC33">
                            	<th>Name</th>
                            	<th>Average time(ns)</th>
                        	</tr>
                        	
                        	<xsl:call-template name="API_Results">                       	
                        	<xsl:with-param name="domaine">0</xsl:with-param>
                        	</xsl:call-template>   
                        	
						</table>
						<br /><br />

                    <h2>Bytecodes execution time</h2>
                        <table border="1" cellpadding="5">
                        	<tr bgcolor="#FFCC33">
                            	<th>Name</th>
                            	<th>Average time(ns)</th>
                        	</tr>

                        	<xsl:call-template name="VM_Results">                       	
                        	<xsl:with-param name="domaine">0</xsl:with-param>
                        	</xsl:call-template>   
                      
                    </table>
                    
                    <br /><br />

				<h1>List of the played bench scripts</h1>				
				<xsl:call-template name="list_scripts">                       	
      			</xsl:call-template> 
</xsl:template>





<xsl:template name="french_body">

				<h1>Evaluation (nom) du <xsl:value-of select="Bench/Test/@date"/></h1>
                <p>
                    ATR : <xsl:value-of select="Bench/@atr"/><br />
                    Lecteur : <xsl:value-of select="managerConfig/cad"/><br />
                    Machine H&#244;te : <xsl:value-of select="Environnement/@host"/><br />
                    VM Java : <xsl:value-of select="Environnement/@VMJava"/><br />
                    Version Benchmark : (version svn)<br />
                    Dur&#233;e totale d'ex&#233;cution : (dur&#233;e)<br />
                </p>
                <br />

			    <h1>Indice de Confiance</h1>
                <p>
                    Filtrage : (indice de filtrage)<br />
                    Indice de confiance : (valeur fix&#233;e)<br />
                    
                </p>
                <br />

				<h1>R&#233;sultat global</h1>
                <p>
                    Note G&#233;n&#233;rale : <xsl:value-of select="Results/@mark"/><br /><br />
                    
                    <img src="charts/notes_chart.png"/><br />
                    <i>graphe synth&#233;tique des notes par domaine</i><br />
                    <br />
                    Note Transport : <xsl:value-of select="Results/Domain[position()=1]/@mark"/><br />
                    Note Bancaire : <xsl:value-of select="Results/Domain[position()=3]/@mark"/><br />
                    Note Identit&#233; : <xsl:value-of select="Results/Domain[position()=2]/@mark"/><br />
                </p>
                <br />

				<h1>Resultat d&#233;taill&#233;</h1>
                <p>
                    <h2>Transport</h2>
                        <h3>APIs</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Transport&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    <table border="1">
                   	<tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>
                        
                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">1</xsl:with-param>
                        	</xsl:call-template>
                        
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_transport.png"/> </td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>
                    <br /><br />

                        <h3>VM</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Transport&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">1</xsl:with-param>
                        	</xsl:call-template>
                            
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr> <td><img src="charts/chart_vm_transport.png"/> </td> </tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>    
                    <br /><br /><br />

                    <h2>Bancaire</h2>
                        <h3>APIs</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Bancaire&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>

                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">3</xsl:with-param>
                        	</xsl:call-template>
                        
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_bancaire.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />

                        <h3>VM</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Bancaire&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>                    
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">3</xsl:with-param>
                        	</xsl:call-template>
                                           
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_vm_bancaire.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br /><br />

                    <h2>Identit&#233;</h2>
                        <h3>APIs</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Identit&#233;&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>                    
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>

                        	<xsl:call-template name="API_Results">
                        	<xsl:with-param name="domaine">2</xsl:with-param>
                        	</xsl:call-template>  
                      
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_api_identite.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />

                        <h3>VM</h3>

                    Liste des principaux indices de performance (tri&#233;s par ordre d'importance) dans le cadre des applications &lt;&lt;Identit&#233;&gt;&gt;. Seules les dix op&#233;rations les plus significatives sont report&#233;es ici, pour le d&#233;tail, consultez la section <a href="#ResultatBrut">R&#233;sultat brut</a>.<br />
                    <br />
                    
                    <table border="1">
                    <tr>
                    <td>                    
                    <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                            <th>Coef. d'impact</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">
                        	<xsl:with-param name="domaine">2</xsl:with-param>
                        	</xsl:call-template>  
                      
                    </table>
                    </td>
                    
                    <td valign="top">
                    <table border="1" cellpadding="5">
                    	<tr bgcolor="#FFCC33">
                            <th>Graphique</th>
                    	</tr>
                    	<tr><td><img src="charts/chart_vm_identite.png"/></td></tr>
                    </table>
                    </td>
                    
                    </tr>
					</table>  
                    <br /><br />
                </p>

                <a name="ResultatBrut"><h1>R&#233;sultat brut</h1></a>
                    <h2>Temps d'ex&#233;cution des APIs</h2>
                        <table border="1" cellpadding="5">
                        	<tr bgcolor="#FFCC33">
                            		<th>Nom</th>
                            		<th>Dur&#233;e (ns)</th>
                        	</tr>
       
                        	<xsl:call-template name="API_Results">                    
   	
                        	<xsl:with-param name="domaine">0</xsl:with-param>
                        	</xsl:call-template>   

                        </table>
						<br /><br />

                    <h2>Temps d'ex&#233;cution des bytecodes</h2>
                        <table border="1" cellpadding="5">
                        <tr bgcolor="#FFCC33">
                            <th>Nom</th>
                            <th>Dur&#233;e (ns)</th>
                        </tr>

                        	<xsl:call-template name="VM_Results">                       	
                        	<xsl:with-param name="domaine">0</xsl:with-param>
                        	</xsl:call-template>   
                      
                    </table>
                    
                    <br /><br />

				<h1>Liste des scripts de bench jou&#233;s</h1>				
				<xsl:call-template name="list_scripts">                       	
      			</xsl:call-template> 
</xsl:template>

<xsl:template name="API_Results">

	<xsl:param name="domaine" />
	<xsl:param name="totalocc" 
		select="Results/Domain[position()=$domaine]/Methods/@totalocc 
		+ Results/Domain[position()=$domaine]/Bytecodes/@totalocc"
		/>
	
	<xsl:choose>
		<xsl:when test="$domaine = 0">
			<xsl:for-each select="ExtractedResult/Metric[position()= 1]/Unit[@benchedmode='api']">
				<tr>
					<td><xsl:value-of select="@name"/><br /></td>
					<td><xsl:value-of select="Time/@avg"/><br /></td>
				</tr>
			</xsl:for-each>
		</xsl:when>
		<xsl:otherwise>
			<xsl:for-each select="Results/Domain[position()=$domaine]/Methods/Method">
			<xsl:sort  order="descending" data-type="number" select="@nbocc"/>
				<xsl:if test="10 &gt;= position()">
				<tr>
					<td><xsl:value-of select="@name"/><br /></td>
					<td><xsl:value-of select="@avg"/><br /></td>
					<td><xsl:value-of select="@nbocc div $totalocc"/><br /></td>		
				</tr>	
				</xsl:if>					
			</xsl:for-each>
        </xsl:otherwise>        
	</xsl:choose>
</xsl:template>





<xsl:template name="VM_Results">

	<xsl:param name="domaine" />
	<xsl:param name="totalocc" 
		select="Results/Domain[position()=$domaine]/Methods/@totalocc 
		+ Results/Domain[position()=$domaine]/Bytecodes/@totalocc"
		/>
	
	<xsl:choose>
		<xsl:when test="$domaine = 0">
			<xsl:for-each select="ExtractedResult/Metric[position()= 1]/Unit[@benchedmode='vm']">
				<tr>
					<td><xsl:value-of select="@name"/><br /></td>
					<td><xsl:value-of select="Time/@avg"/><br /></td>
				</tr>
			</xsl:for-each>
		</xsl:when>
		<xsl:otherwise>
			<xsl:for-each select="Results/Domain[position()=$domaine]/Bytecodes/Bytecode">
			<xsl:sort  order="descending" data-type="number" select="@nbocc"/>
				<xsl:if test="10 &gt;= position()">
				<tr>
					<td><xsl:value-of select="@name"/><br /></td>
					<td><xsl:value-of select="@avg"/><br /></td>
					<td><xsl:value-of select="@nbocc div $totalocc"/><br /></td>		
				</tr>	
				</xsl:if>					
			</xsl:for-each>
        </xsl:otherwise>        
	</xsl:choose>
</xsl:template>

<xsl:template name="list_scripts">
		
	<xsl:choose>
		<xsl:when test="count(managerConfig/tests/test)&gt; 1">
				<xsl:if test="@lang = 'en'">
					<xsl:value-of select="count(managerConfig/tests/test)"/> scripts were played. <br />
      			</xsl:if>
				<xsl:if test="@lang = 'fr'">
					<xsl:value-of select="count(managerConfig/tests/test)"/> scripts ont &#233;t&#233; jou&#233;. <br />
      			</xsl:if>
		</xsl:when>	
		<xsl:when test="count(managerConfig/tests/test)= 0"> 
				<xsl:if test="@lang = 'en'">
					No script was played. <br />
      			</xsl:if>
				<xsl:if test="@lang = 'fr'">
					Aucun script n'a &#233;t&#233; jou&#233;. <br />
      			</xsl:if>
		</xsl:when>	
		<xsl:otherwise>
				<xsl:if test="@lang = 'en'">
					<xsl:value-of select="count(managerConfig/tests/test)"/> script was played. <br />
      			</xsl:if>
				<xsl:if test="@lang = 'fr'">
					<xsl:value-of select="count(managerConfig/tests/test)"/> script a &#233;t&#233; jou&#233;. <br />
      			</xsl:if>

		</xsl:otherwise>
	</xsl:choose>

	<ul>
	<xsl:for-each select="managerConfig/tests/test">
		<li><xsl:value-of select="@testConfig"/></li>
	</xsl:for-each>
	</ul>
	
</xsl:template>

<!--
<xsl:template name="list_scripts">
		
	<xsl:choose>
		<xsl:when test="count(managerConfig/tests/test)&gt; 1">
			<xsl:value-of select="count(managerConfig/tests/test)"/> scripts ont &#233;t&#233; jou&#233;. <br />
		</xsl:when>	
		<xsl:when test="count(managerConfig/tests/test)= 0"> 
			Aucun script n'a &#233;t&#233; jou&#233;. <br />
		</xsl:when>	
		<xsl:otherwise>
			<xsl:value-of select="count(managerConfig/tests/test)"/> script a &#233;t&#233; jou&#233;. <br />
		</xsl:otherwise>
	</xsl:choose>

	<ul>
	<xsl:for-each select="managerConfig/tests/test">
		<li><xsl:value-of select="@testConfig"/></li>
	</xsl:for-each>
	</ul>
	
</xsl:template>
-->

</xsl:stylesheet>