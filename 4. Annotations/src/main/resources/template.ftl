<form action=${action}  method=${method}>
    <#list annotation as item>
        <input ${item}>
    </#list>
    <input type="submit" value="Отправить">
</form>