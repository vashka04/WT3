<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Phohe Details">
    <div class="container">
        <div id="statusMessage" hidden>
            <div id="statusMessageHead" class="panel-heading"></div>
            <div id="statusMessageBody" class="panel-body"></div>
        </div>
    </div>
    <div class="panel"></div>
    <div class="container">
        <h2>${ball.model}</h2>
        <div class="row">
            <div class="col-6">
                <img class="rounded" src="https://raw.githubusercontent.com/andrewosipenko/ballshop-ext-images/master/${ball.imageUrl}">
                <p class="text-justify">${ball.description}</p>
                <div class="float-right">
                    <p class="text">Price: $${ball.price}</p>
                    <input id="quantity${ball.id}">
                    <input id="add-to-cart" class="btn btn-outline-dark border-dark" type="button" onclick="addToCart(${ball.id});" value="Add to Cart"/>
                    <p class="text-danger" id="statusMessage${ball.id}"></p>
                </div>

            </div>

            <div class="col-1"></div>

            <div class="col-4">
                <h3>Weight</h3>
                <table class="table table-bordered table-light container-fluid">
                    <tr>
                        <td>Weight</td>
                        <td>${ball.weight} g</td>
                    </tr>
                </table>
            </div>

            <div class="col-1"></div>
        </div>
    </div>
</tags:master>