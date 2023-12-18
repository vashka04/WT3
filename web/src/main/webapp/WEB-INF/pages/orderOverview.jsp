<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Order overview">
  <p></p>
  <div id="statusMessage" class="container"><span></span></div>
  <c:if test="${not empty successMessage}">
    <div class="container">
      <div class="panel panel-success">
        <div class="panel-heading">Success</div>
        <div class="panel-body">${successMessage}</div>
      </div>
    </div>
  </c:if>
  <c:if test="${not empty errorMessage}">
    <div class="container">
      <div class="panel panel-danger">
        <div class="panel-heading">Error</div>
        <div class="panel-body">${errorMessage}</div>
      </div>
    </div>
  </c:if>
  <div class="container">
    <h1>Order overview</h1>
    <h2>Order status: ${order.status}</h2>
  </div>
  <div class="panel"></div>
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8">
      <c:if test="${empty order}">
        <h1 class="text-center">Order not found</h1>
      </c:if>
      <c:if test="${not empty order}">
        <table class="table table-bordered text-center">
          <thead>
          <tr class="bg-light">
            <td>
              Brand
            </td>
            <td>
              Model
            </td>
            <td>
              Color
            </td>
            <td>
              Display size
            </td>
            <td>
              Quantity
            </td>
            <td>
              Price
            </td>
          </tr>
          </thead>
          <c:forEach var="item" items="${order.orderItems}">
            <tr>
              <td class="align-middle">
                  ${item.ball.brand}
              </td>
              <td class="align-middle">
                  ${item.ball.model}
              </td>
              <td class="align-middle">
                  ${item.ball.price}
              </td>
            </tr>
          </c:forEach>
          <tr>
            <td class="border-white"></td><td class="border-white"></td><td class="border-white"></td><td class="border-white"></td>
            <td>
              Subtotal
            </td>
            <td>
                ${order.subtotal}
            </td>
          </tr>
          <tr>
            <td class="border-white"></td><td class="border-white"></td><td class="border-white"></td><td class="border-white"></td>
            <td>
              Delivery
            </td>
            <td>
                ${order.deliveryPrice}
            </td>
          </tr>
          <tr>
            <td class="border-white"></td><td class="border-white"></td><td class="border-white"></td><td class="border-white"></td>
            <td>
              TOTAL
            </td>
            <td>
                ${order.totalPrice}
            </td>
          </tr>
        </table>

        <table class="table-borderless">
          <tr>
            <td class="align-top">
              First name:
            </td>
            <td class="align-top">
                ${order.firstName}
            </td>
          </tr>
          <tr>
            <td class="align-top">
              Last name:
            </td>
            <td class="align-top">
                ${order.lastName}
            </td>
          </tr>
          <tr>
            <td class="align-top">
              Delivery address:
            </td>
            <td class="align-top">
                ${order.deliveryAddress}
            </td>
          </tr>
          <tr>
            <td class="align-top">
              Contact phone:
            </td>
            <td class="align-top">
                ${order.contactPhoneNo}
            </td>
          </tr>
        </table>
        <p>${order.additionalInformation}</p>
      </c:if>
    </div>

    <div class="col-2"></div>
  </div>
</tags:master>