<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nightingale.Constants"%>
<c:set var="currencies"
    value="<%=Constants.Currency.AVAILABLES%>" />

<p class="text-danger">
	<spring:message code="${error}" text="${error}"/>
</p>
<p class="text-danger">
	<form:errors path="article" />
</p>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="name" />
	</label>
	<div class="col-sm-8">
		<spring:message code="name" var="namePlaceholder" />
		<form:input path="article.name" type="text" class="form-control"
			placeholder="${namePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.name" />
	</p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="code" />
	</label>
	<div class="col-sm-8">
		<spring:message code="code" var="codePlaceholder" />
		<form:input path="article.code" type="text" class="form-control"
			placeholder="${codePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.code" />
	</p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="description" />
	</label>
	<div class="col-sm-8">
		<spring:message code="description" var="descriptionPlaceholder" />
		<form:input path="article.description" type="text" class="form-control"
			placeholder="${descriptionPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.description" />
	</p>
</div>

<div class="form-group">
    <label class="col-sm-4 col-form-label"> <spring:message
            code="price" />
    </label>
    <div class="col-sm-8">
        <spring:message code="price" var="pricePlaceholder" />
        <form:input path="article.price" type="text" class="form-control"
            placeholder="${pricePlaceholder}" />
    </div>
    <p class="text-danger">
        <form:errors path="article.price" />
    </p>
</div>

<div class="form-group">
    <label class="col-sm-4 col-form-label"> <spring:message
            code="currency" />
    </label>
    <div class="col-sm-8">
        <spring:message code="currency" var="currencyPlaceholder" />
        <form:select path="article.currency" type="text" class="form-control" items = "${currencies}"
            placeholder="${currencyPlaceholder}" />
    </div>
    <p class="text-danger">
        <form:errors path="article.currency" />
    </p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="priority" />
	</label>
	<div class="col-sm-8">
		<spring:message code="priority" var="priorityPlaceholder" />
		<form:input path="article.priority" type="text" class="form-control"
			placeholder="${priorityPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.priority" />
	</p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="condition" />
	</label>
	<div class="col-sm-8">
		<form:select class="form-control" path="conditions" multiple="multiple" data-role="tagsinput" items="${articleDTO.conditions}"></form:select>
	</div>
	<p class="text-danger">
		<form:errors path="conditions" />
	</p>
</div>

<div class="form-group">
    <label class="col-sm-4 col-form-label"> <spring:message
            code="categories" />
    </label>
    <div class="col-sm-8">
        <form:select class="form-control" path="categories" multiple="multiple" data-role="tagsinput" items="${articleDTO.categories}"></form:select>
    </div>
    <p class="text-danger">
        <form:errors path="categories" />
    </p>
</div>

<div class="form-group">
    <label class="col-sm-4 col-form-label"> <spring:message
            code="authors" />
    </label>
    <div class="col-sm-8">
        <form:select class="form-control" path="authors" multiple="multiple" data-role="tagsinput" items="${articleDTO.authors}"></form:select>
    </div>
    <p class="text-danger">
        <form:errors path="authors" />
    </p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="tag" />
	</label>
	<div class="col-sm-8">
		<form:select class="form-control" path="tags" multiple="multiple" data-role="tagsinput" items="${articleDTO.tags}"></form:select>
	</div>
	<p class="text-danger">
		<form:errors path="tags" />
	</p>
</div>


<div class="form-group">
	<div class="col-sm-8 col-sm-offset-4">
		<label class="form-check-label"> <form:checkbox path="article.enabled"
				id="enabled" /> <spring:message code="enabled" />
		</label>
		<p class="text-danger">
			<form:errors path="article.enabled" />
		</p>
	</div>
</div>

<c:url value='<%="/data/json/tags?tagType="+Constants.TagTypes.CATEGORIES%>' var='tagCategoriesURL'/>
<c:url value='<%="/data/json/tags?tagType="+Constants.TagTypes.AUTHORS%>' var='tagAuthorsURL'/>
<c:url value='<%="/data/json/tags?tagType="+Constants.TagTypes.BOOK_TAG%>' var='tagURL'/>
<c:url value='<%="/data/json/tags?tagType="+Constants.TagTypes.CONDITION%>' var='conditionURL'/>

<script>
$.get( "${tagCategoriesURL}", function( data ) {
	  var categories = new Bloodhound({
	        datumTokenizer : Bloodhound.tokenizers.whitespace,
	        queryTokenizer : Bloodhound.tokenizers.whitespace,
	        local : data
	    });

	    $('#categories').tagsinput({
	        typeaheadjs : {
	            name : 'categories',
	            source : categories.ttAdapter()
	        }
	    });
	});
	
$.get( "${tagAuthorsURL}", function( data ) {
    var authors = new Bloodhound({
          datumTokenizer : Bloodhound.tokenizers.whitespace,
          queryTokenizer : Bloodhound.tokenizers.whitespace,
          local : data
      });

      $('#authors').tagsinput({
          typeaheadjs : {
              name : 'authors',
              source : authors.ttAdapter()
          }
      });
  });
  
$.get( "${tagURL}", function( data ) {
    var tags = new Bloodhound({
          datumTokenizer : Bloodhound.tokenizers.whitespace,
          queryTokenizer : Bloodhound.tokenizers.whitespace,
          local : data
      });

      $('#tags').tagsinput({
          typeaheadjs : {
              name : 'tags',
              source : tags.ttAdapter()
          }
      });
  });
  
$.get( "${conditionURL}", function( data ) {
    var conditions = new Bloodhound({
          datumTokenizer : Bloodhound.tokenizers.whitespace,
          queryTokenizer : Bloodhound.tokenizers.whitespace,
          local : data
      });

      $('#conditions').tagsinput({
          typeaheadjs : {
              name : 'conditions',
              source : conditions.ttAdapter()
          }
      });
  });



</script>
