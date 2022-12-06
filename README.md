# Aclaraciones

Si tira error la DB, hay que cambiar el update en el persistence por create-drop, y después volver a ejecutar el update
si no se puede conectar a la DB es porque cuando amazon detecta que la conexión está idle, la DB aparece 
'detenida temporalmente' y hay que reiniciarla manualmente desde RDS. A partir de ahí, en teoría, ya funcionaría.
El puerto de spark hay que ver si 8080 o 9090, porque a mí particularmente no me funciona en el 8080.
