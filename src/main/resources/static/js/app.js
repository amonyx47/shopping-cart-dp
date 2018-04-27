jQuery(document).ready(function($){
	updateCartItemCount();
});

	function updateCartItemCount()
	{
		$.ajax ({ 
	        url: '/cart/items/count', 
	        type: "GET", 
	        dataType: "json",
	        contentType: "application/json",
	        complete: function(responseData, status, xhttp){ 
	        	$('#cart-item-count').text('('+responseData.responseJSON.count+')');
	        }
	    });
	}

	function addItemToCart(cod)
	{
		$.ajax ({ 
	        url: '/cart/items', 
	        type: "POST", 
	        dataType: "json",
	        contentType: "application/json",
	        data : '{"cod":"'+ cod +'"}"',
	        complete: function(responseData, status, xhttp){
	        	updateCartItemCount();
	        }
	    }); 
	}

	function updateCartItemQuantity(cod, quantity)
	{
		$.ajax ({ 
	        url: '/cart/items', 
	        type: "PUT", 
	        dataType: "json",
	        contentType: "application/json",
	        data : '{ "product" :{ "cod":"'+ cod +'"},"quantity":"'+quantity+'"}',
	        complete: function(responseData, status, xhttp){ 
	        	updateCartItemCount();        	
	        	location.href = '/cart' 
	        }
	    });
	}

	function removeItemFromCart(cod)
	{
		$.ajax ({ 
	        url: '/cart/items/'+cod,
	        type: "DELETE", 
	        dataType: "json",
	        contentType: "application/json",
	        complete: function(responseData, status, xhttp){ 
	        	updateCartItemCount();
	        	location.href = '/cart' 
	        }
	    });
	}

