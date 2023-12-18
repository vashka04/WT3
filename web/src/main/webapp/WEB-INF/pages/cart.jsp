<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Cart">
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
    <h2>Cart</h2>
  </div>
  <div class="panel"></div>
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8">
      <c:if test="${cart.items.size() > 0}">
        <form action="<c:url value="/order"/>" method="get">
          <button class="btn btn-lg btn-light float-right" type="submit">Order</button>
        </form>
        <frm:form method="put" modelAttribute="cartItemsQuantities" action="${pageContext.servletContext.contextPath}/cart">
          <table class="table table-hover table-bordered text-center">
            <thead>
            <tr class="bg-light">
              <td>
                Brand
              </td>
              <td>
                Model
              </td>
              <td>Color</td>
              <td>
                Display size
              </td>
              <td>
                Price
              </td>
              <td>Quantity</td>
              <td>Action</td>
            </tr>
            </thead>
            <c:forEach var="i" begin="0" end="${cart.items.size() - 1}">
              <tr id="row${cart.items[i].ball.id}">
                <td class="align-middle">
                  <a href="<c:url value="/productDetails/${cart.items[i].ball.id}"/>">${cart.items[i].ball.brand}</a>
                </td>
                <td class="align-middle">
                  <a href="<c:url value="/productDetails/${cart.items[i].ball.id}"/>">${cart.items[i].ball.model}</a>
                </td>
                <td class="align-middle">$ ${cart.items[i].ball.price}</td>
                <td class="align-middle">
                  <frm:input path="items[${i}].quantity"/>
                  <frm:hidden path="items[${i}].ballId"/>
                  <c:if test="${not empty validationErrors[i]}">
                    <p class="text-danger">${validationErrors[i]}</p>
                  </c:if>
                  <c:if test="${not empty outOfStockErrors[i]}">
                    <p class="text-danger">${outOfStockErrors[i]}</p>
                  </c:if>
                </td>
                <td class="align-middle">
                  <button form="deleteCartItem" class="btn btn-outline-dark border-dark"
                          formaction="<c:url value="/cart?ballId=${cart.items[i].ball.id}"/>">Delete</button>
                </td>
              </tr>
            </c:forEach>
          </table>
          <p>
            <button class="btn btn-lg btn-outline-light text-dark border-dark float-right" type="submit">Update</button>
          </p>
        </frm:form>
        <form id="deleteCartItem" method="post">
          <input type="hidden" name="_method" value="delete">
        </form>
      </c:if>
    </div>

    <div class="col-2"></div>
  </div>
</tags:master>
