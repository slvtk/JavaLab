<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Заявление на увольнение</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="jumbotron">
            <div class="row justify-content-center">
                <h1 class="display-4">Заявление<span class="badge badge-success">${form_id}</span></h1>
            </div>
            <div class="row justify-content-center">
                <p class="lead">Я, ${user_name} ${user_surname}, в возрасте ${user_age}, хочу подать заявление на
                    увольнение.</p>
            </div>
            <hr class="my-4">
            <div class="row justify-content-center">
                <p>${form_date}</p>
            </div>
            <div class="row justify-content-center">
                <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
            </div>
        </div>
    </div>
</div>
</html>