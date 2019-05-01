$(function() {

	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	
	// code for jquery dataTable
	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';

								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									
									if(userRole !== 'ADMIN') {
										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {
	
											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}
									else {
										str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									}
									
									return str;

								}

							} ]
				});
	}
	/*------*/
	/* for fading out the alert message after 3 seconds */
	$alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}

	//

//	$('.switch input[type="checkbox"]')
//			.on(
//					'change',
//					function() {
//						var checkbox = $(this);
//						var checked = checkbox.prop('checked');
//						var dMsg = (checked) ? 'You wanrt to activate the product?'
//								: 'You wanrt to deactivate the product?';
//						var value = checkbox.prop('value');
//
//						bootbox
//								.confirm({
//									size : 'medium',
//									title : 'Product Activation/Deactivation',
//									message : dMsg,
//									callback : function(confirmed) {
//										if (confirmed) {
//											console.log(value);
//											bootbox
//													.alert({
//														size : 'medium',
//														title : 'Information',
//														message : 'you are going to perform operation on product'
//																+ value
//													});
//										} else {
//											checkbox.prop('checked', !checked);
//										}
//									}
//								});
//					});

	// Datatable for admin
	var $adminProductsTable = $('#adminProductsTable');

	if ($adminProductsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		console.log(jsonUrl);

		$adminProductsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},

							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&euro; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str ='';
									str +='<label class="switch">';
									if (data) {
										str +='<input type="checkbox" checked="checked" value="'+ row.id+'" />';

									} else {
										str += '<input type="checkbox" value="'+row.id+'" />';  
									}
									str +='<div class="slider round"> </div></label>';
									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'+contextRoot+'/manage/'+data+'/product" class="btn btn-primary">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';
									return str;
								}
							} ],

					initComplete : function() {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change',function() {

											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You wanrt to activate the product?'
													: 'You wanrt to deactivate the product?';
											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation/Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);
																bootbox
																		.alert({
																			size : 'medium',
																			title : 'Information',
																			message : 'you are going to perform operation on product'
																					+ value
																		});
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});
										});

					}

				});
	}
	
	// validating the product form element	
	// fetch the form element
	$categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({			
				rules: {
					name: {
						required: true,
						minlength: 3
					},
					description: {
						required: true,
						minlength: 5					
					}				
				},
				messages: {					
					name: {
						required: 'Please enter category name!',
						minlength: 'Please enter at least 2 characters'
					},
					description: {
						required: 'Please enter category name!',
						minlength: 'Please enter at least 5 characters'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					error.addClass('help-block');
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
	
/*validating the loginform*/
	
	// validating the product form element	
	// fetch the form element
	$loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
						
					},
					password: {
						required: true
					}				
				},
				messages: {					
					username: {
						required: 'Please enter your email!',
						email: 'Please enter a valid email address!'
					},
					password: {
						required: 'Please enter your password!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");
					
					// add the error label after the input element
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
		

});