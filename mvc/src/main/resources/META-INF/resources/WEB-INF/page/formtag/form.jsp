<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		The firstName and lastName values are retrieved from the command object placed 
		in the PageContext by the page controller。
		The preceding JSP assumes that the variable name of the form backing object is 'command'. 
		If you have put the form backing object into the model under another name (definitely a best practice), 
		then you can bind the form to the named variable
	 -->
	<form:form commandName="user" action="save">
		<table>
			<!-- ===== The input tag begin =====  -->
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<!-- ===== The input tag end =====  -->
			
			<!-- =====The checkbox tag begin =====  -->
			<tr>
				<td>Subscribe to newsletter?:</td>
				<%-- Approach 1: Property is of type java.lang.Boolean --%>
				<td><form:checkbox path="preferences.receiveNewsletter" /></td>
			</tr>
			
			<tr>
				<td>Interests:</td>
				<%-- Approach 2: Property is of an array or of type java.util.Collection --%>
				<td>
					Quidditch: <form:checkbox path="preferences.interests" value="Quidditch" /> 
					Herbology: <form:checkbox path="preferences.interests" value="Herbology" /> 
					Defence Against the Dark Arts: <form:checkbox path="preferences.interests" value="Defence Against the Dark Arts" />
				</td>
			</tr>

			<tr>
				<td>Favourite Word:</td>
				<%-- Approach 3: Property is of type java.lang.Object --%>
				<td>Magic: <form:checkbox path="preferences.favouriteWord" value="Magic" />
				</td>
			</tr>
			<!-- ===== The checkbox tag end ===== -->
			
			<!-- ===== The checkboxes tag begin ===== -->
			<tr>
	            <td>Interests:</td>
	            <td>
	                <%-- Property is of an array or of type java.util.Collection(可以是数组或者Collection)
	                	Map, the map entry key will be used as the value and the map entry’s value will be used as the label to be displayed
	                 --%>
	                <form:checkboxes path="preferences.interests" items="${interestList}"/>
	            </td>
	        </tr>
	        <!-- ===== The checkboxes tag end ===== -->
	        
	        <!-- ===== The radiobutton tag begin ===== -->
	        <tr>
			    <td>Sex:</td>
			    <td>
			        Male: <form:radiobutton path="sex" value="M"/> 
			        Female: <form:radiobutton path="sex" value="F"/>
			    </td>
			</tr>
			<!-- ===== The radiobutton tag end ===== -->
			
			<!-- ===== The radiobuttons tag begin ===== -->
			<!-- 
			 You pass in an Array, a List or a Map containing the available options in the "items" property. 
			 In the case where you use a Map, the map entry key will be used as the value and the map entry’s 
			 value will be used as the label to be displayed
			 -->
			<tr>
			    <td>Sex:</td>
			    <td><form:radiobuttons path="sex" items="${sexOptions}"/></td>
			</tr>
			<!-- ===== The radiobuttons tag end ===== -->
			
			<!-- ===== The Password tag begin ===== -->
			<tr>
			    <td>Password:</td>
			    <td>
			        <form:password path="password"/>
			    </td>
			</tr>
			<!-- ===== The Password tag end ===== -->
			
			<!-- ===== The select tag begin ===== -->
			<tr>
			    <td>Skills:</td>
			    <td><form:select multiple="false" path="skills" items="${skills}"/></td>
			</tr>
			<!-- ===== The select tag end ===== -->
			
			<!-- ===== The select option tag begin ===== -->
			<tr>
			    <td>House:</td>
			    <td>
			        <form:select path="house">
			            <form:option value="Gryffindor"/>
			            <form:option value="Hufflepuff"/>
			            <form:option value="Ravenclaw"/>
			            <form:option value="Slytherin"/>
			        </form:select>
			    </td>
			</tr>
			<!-- ===== The select option tag end ===== -->
			
			<!-- ===== The select options tag begin ===== -->
			<tr>
			    <td>Country:</td>
			    <td>
			        <form:select path="country">
			            <form:option value="-" label="--Please Select"/>
			            <!-- countryList是对象List，对象包含两个属性code和name  -->
			            <form:options items="${countryList}" itemValue="code" itemLabel="name"/>
			        </form:select>
			    </td>
			</tr>
			<!-- ===== The select options tag end ===== -->
			
			<!-- ===== The textarea tag begin ===== -->
			<tr>
			    <td>Notes:</td>
			    <td><form:textarea path="notes" rows="3" cols="20"/></td>
			    <td><form:errors path="notes"/></td>
			</tr>
			<!-- ===== The textarea tag end ===== -->
			
			<!-- ===== The hidden  tag begin ===== -->
			<form:hidden path="house"/>
			<!-- ===== The hidden  tag end ===== -->
			

			<tr>
				<td colspan="2"><input type="submit" value="Save Changes" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>