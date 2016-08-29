<?php include 'inc/top.inc.php';
?>

<body>
	<table width="500" border="0" cellspacing="0" cellpadding="2">
		<tr>
			<td>
				<form action="addDo.php" method="post">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><h2>Add A New Business</h2></td>
								<td align="right" valign="bottom" style="padding-bottom:4px;padding-right:4px;">
									<div>
										<span>All Fields Are Required</span>
									</div>
									<hr/>
				
									
							</td>
						</tr>

								<tr>
									<td colspan="2" align="center" valign="top" class="cagBorder">
										<table border="0" cellspacing="0" cellpadding="4">
											
											<tr>
												<td valign="top">Business Name:</td>
												<td valign="top">
													<input type="hidden" name="id2" value=""/>
													<input type="text" name="name" size="41" maxlength="30" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address:</td>
												<td valign="top">
													<input type="text" name="address" size="41" maxlength="40" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address 2:</td>
												<td valign="top">
													<input type="text" name="address2" size="41" maxlength="50" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">City:</td>
												<td valign="top">
													<input type="text" name="city" size="41" maxlength="20" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">State:</td>
												<td valign="top">
													<input type="text" name="state" size="41" maxlength="2" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Zip:</td>
												<td valign="top">
													<input type="text" name="zip" size="41" maxlength="11" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Phone:</td>
												<td valign="top">
													<input type="text" name="phone" size="41" maxlength="15" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Latitude:</td>
												<td valign="top">
													<input type="text" name="latitude" size="41" maxlength="11" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Longitude:</td>
												<td valign="top">
													<input type="text" name="longitude" size="41" maxlength="11" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Photo:</td>
												<td valign="top">
													<input type="text" name="photo" size="41" maxlength="30" value=""/>
												</td>
											</tr>
											<tr>
												<td valign="top">Description:</td>
												<td valign="top">
													<input type="text" name="description" size="41" maxlength="70" value=""/>
												</td>
											</tr>

										</table>
									</td>
								</tr>
														<tr>
							<td colspan="4" align="center">
								<div style="margin-top:10px;">
									<input type="submit" value="  Save  " class="" style="margin:4px;"/>
									<input type="button" name="cancel" value="Cancel" class=""  onclick="window.location = 'index.php'" style="margin:4px;"/>
								</div>
							</td>
						</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
	<?php include 'inc/bottom.inc.php';?>