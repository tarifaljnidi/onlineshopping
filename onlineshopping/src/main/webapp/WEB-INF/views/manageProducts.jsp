<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<c:if test="${not empty message}">

			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>

		</c:if>


		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Product Management</h4>

				</div>

				<!-- 				<div class="panel-body"> -->
				<%-- 					<sf:form class="form-horizontal"  modelAttribute="product" > --%>
				<!-- 						<div class="form-group"> -->
				<!-- 							<label class="control-label col-md-4" for="name">Enter -->
				<!-- 								Product Name :</label> -->
				<!-- 							<div class="col-md-8"> -->
				<%-- 								<sf:input type="text" path="name" id="name" --%>
				<%-- 									placeholder="Product Name" class="form-control" /> <em --%>
				<!-- 									Class="help-block">Please enter Product Name </em> -->
				<!-- 							</div> -->
				<!-- 						</div> -->

				<!-- 						<div class="form-group"> -->
				<!-- 							<label class="control-label col-md-4" for="name">Enter -->
				<!-- 								Brand Name :</label> -->
				<!-- 							<div class="col-md-8"> -->
				<!-- 								<input type="text" name="brand" id="brand" -->
				<!-- 									placeholder="Brand Name" class="form-control" /> <em -->
				<!-- 									Class="help-block">Please enter Brand Name </em> -->
				<!-- 							</div> -->
				<!-- 						</div> -->

				<!-- 							<div class="form-group"> -->
				<!-- 							<div class="col-md-offset-4 col-md-8"> -->
				<!-- 								<input type="submit" name="submit" id="submit" -->
				<!-- 									value="Submit" class="btn btn-primary" />  -->
				<!-- 							</div> -->
				<!-- 						</div> -->



				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4">Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" class="form-control"
									placeholder="Product Name" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Brand</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" class="form-control"
									placeholder="Brand Name" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" class="form-control"
									placeholder="Enter your description here!" />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" class="form-control"
									placeholder="Enter Unit Price" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Quantity</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" class="form-control"
									placeholder="Enter Quantity" />
								<sf:errors path="quantity" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Select an image :</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Select Category</label>
							<div class="col-md-8">
								<sf:select id="categoryId" path="categoryId"
									items="${categories}" itemLabel="name" itemValue="id"
									class="form-control" />
								<div class="text-right">
<%-- 									<c:if test="${Product.id == 0}"> --%>
										<br />
										<button type="button" class="btn btn-warning btn-xs"
											data-toggle="modal" data-target="#myCategoryModal">Add
											New Category</button>
<%-- 									</c:if> --%>
								</div>
							</div>
						</div>

						<div class="form-group">

							<div class="col-md-offset-4 col-md-4">



								<input type="submit" name="submit" value="Save"
									class="btn btn-primary" />
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />

							</div>
						</div>
					</sf:form>

				</div>
			</div>
		</div>
	</div>
	
<!-- Modal -->
	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">New Category</h4>
	      </div>
	      <div class="modal-body">
	        
	        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">
	        	
       			<div class="form-group">
					<label class="control-label col-md-4">Name</label>
					<div class="col-md-8 validate">
						<sf:input type="text" path="name" id="category_name" class="form-control"
							placeholder="Category Name" /> 
					</div>
				</div>
       			
       			<div class="form-group">				
					<label class="control-label col-md-4">Description</label>
					<div class="col-md-8 validate">
						<sf:textarea path="description" class="form-control"
							placeholder="Enter category description here!" id="category_description" /> 
					</div>
				</div>	        	        
	        
	        
				<div class="form-group">				
					<div class="col-md-offset-4 col-md-4">					
						<input type="submit" name="submit" value="Add Gategory" class="btn btn-primary"/>						
					</div>
				</div>	        
	        </sf:form>
	      </div>
	    </div>
	  </div>
	</div>	
	
	<!-- 	products table for admin -->
	<div class="row">

		<div class='col-xs-12'>
			<h3>Available Products</h3>
			<hr />
		</div>

		<div class='col-xs-12'>
			<div style="overflow: auto">

				<table id="adminProductsTable"
					class="table table-sruped table-bordered">

					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Brand</th>
							<th>Name</th>
							<th>Qty. Avail</th>
							<th>Unit Price</th>
							<th>Activate</th>
							<th>Edit</th>
						</tr>
					</thead>

					<!-- 					<tbody> -->
					<!-- 						<tr> -->
					<!-- 							<td>4</td> -->
					<!-- 							<td><img class="adminDataTableImg" -->
					<%-- 								src="${contextRoot}/resources/images/PRDMNO123PQRX.jpg" alt="macbook pro" /></td> --%>
					<!-- 							<td>MacBook Pro</td> -->
					<!-- 							<td>1</td> -->
					<!-- 							<td>1500</td> -->
					<!-- 							<td><label class="switch"> <input type="checkbox" -->
					<!-- 									checked="checked" value="4"/> -->
					<!-- 									<div class="slider"></div> -->
					<!-- 							</label></td> -->
					<%-- 							<td><a href="${contextRoot}/manage/4/product" --%>
					<!-- 								class="btn btn-warning" > <span -->
					<!-- 								class="glyphicon glyphicon-penciil"></span> </a></td> -->
					<!-- 						</tr> -->

					<!-- 						<tr> -->
					<!-- 							<td>4</td> -->
					<!-- 							<td><img class="adminDataTableImg" -->
					<%-- 								src="${contextRoot}/resources/images/PRDMNO123PQRX.jpg" alt="macbook pro" /></td> --%>
					<!-- 							<td>MacBook Pro</td> -->
					<!-- 							<td>Appel</td> -->
					<!-- 							<td>1</td> -->
					<!-- 							<td>1500</td> -->
					<!-- 							<td><label class="switch"> <input type="checkbox" -->
					<!-- 									 value="4"/> -->
					<!-- 									<div class="slider"></div> -->
					<!-- 							</label></td> -->
					<%-- 							<td><a href="${contextRoot}/manage/4/product" --%>
					<!-- 								class="btn btn-warning" > <span -->
					<!-- 								class="glyphicon glyphicon-penciil"></span> </a></td> -->

					<!-- 						</tr> -->
					<!-- 					</tbody> -->

					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Brand</th>
							<th>Name</th>
							<th>Qty. Avail</th>
							<th>Unit Price</th>
							<th>Activate</th>
							<th>Edit</th>
						</tr>
					</tfoot>


				</table>

			</div>
		</div>
	</div>

</div>