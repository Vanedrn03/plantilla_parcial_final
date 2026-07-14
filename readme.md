# [Vanessa Saraí Durán Cruz] [00025822]

## Indicaciones

Recientemente, se utilizó AI para crear un sistema de gestion de una biblioteca, el cual ha generado varios errores, su trabajo es arreglarlo. Dado el siguiente caso de uso, explique y/o resuelva cada problema según se le pida.

---

## Consideraciones

La libreria crea automaticamente un correo con los nombres de la persona

---

## Problemas

### 1. Filtro por autor y género (10%)

QA ha reportado que el endpoint para obtener los libros puede filtrar por **autor** y por **género**, o por cualquiera de los dos de manera individual.

Actualmente:

- Filtrar únicamente por autor funciona correctamente.
- Filtrar únicamente por género funciona correctamente.
- Filtrar por **autor y género al mismo tiempo** provoca que el servidor falle.

**Instrucción:** Explique la causa del problema y resuélvalo.

**La principal causa era el tipo ya que lo estabamos declarando previamente como un Genre pero luego lo estabamos declarando como string por ello el servidor fallaba, ya que no lo habiamos declarado de la misma manera en el repositorio.**

---

### 2. Error al volver a prestar un libro (10%)

Un usuario reportó que al pedir prestado el libro **The Selfish Gene**, devolverlo e intentar pedirlo prestado nuevamente, el servidor falla.

**Instrucción:** Explique la causa del problema y resuélvalo.

**Pasaba o daba error porque al regresarlo nunca lo volviamos a poner disponible, entonces como solo era uno el servidor lanzaba el error que no lo habiamos devuelto**

---

### 3. Cantidad de libros por género (10%)

Existe un endpoint que devuelve la cantidad de libros disponibles por género. Sin embargo, actualmente dicho endpoint falla.

**Instrucción:** Explique la causa del problema y resuélvalo.

**El error que daba era porque al buscarlo lo hacian siempre o quizas con minusculas, o mezcla de mayus y minus, sin embargo el genero en la base de datos esta en mayuscula, ahora forzamos a que siempre busque en mayuscula al convertirlo con la funcion genre.toUpperCase())**

---

### 4. Error al consultar un libro por ID (10%)

Un miembro del equipo de frontend reporta que la siguiente llamada falla:

```http
GET /books?id=ed16ed1e-7017-4697-a08a-d28c09a74acf
```

**Instrucción:** Explique la causa del problema.

**Falla ya que no es la manera correcta de poder traer un libro esta deberia de ser:**

```http
GET /books/ed16ed1e-7017-4697-a08a-d28c09a74acf
```

---

### 5. Error al crear un libro (10%)

QA ha reportado que el siguiente payload enviado al endpoint `POST /books` provoca un error:

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "genre": "classic",
  "isbn": "978-0132350884",
  "available": true,
  "availableCount": 5
}
```

**Instrucción:** Explique la causa del problema.

**Porque no estamos enviando el tipo de dato correcto en el genero del libro**

---

### 6. Devolución de libros no prestados (20%)

QA ha reportado que un usuario es capaz de devolver libros que nunca ha solicitado en préstamo.

**Instrucción:**

- Confirme si este comportamiento es realmente posible.
- Si es posible, explique la causa y resuelva el problema.
- Si no es posible, explique por qué, haciendo referencia al código correspondiente.

**Este error pasaba ya que no teniamos declarado esta validacion, ahora si quieren devolver el libro traemos el estado del libro y si esta persona no a prestado ese libro y quiere devolver ese libro le decimos que ese usuario no a usado ese libro. El codigo modificado fue en Movement Service**

```java
if (type == MovementType.BORROWING) {
        if (!book.isAvailable()) {
        throw new RuntimeException("Book is not available");
            }
                    book.setAvailableCount(book.getAvailableCount() - 1);
        if (book.getAvailableCount() == 0) {
        book.setAvailable(false);
            }
} else {
        Movement lastMovement = movementRepository
        .findTopByLectorAndBookOrderByTimestampDesc(lector, book)
        .orElse(null);
            if (lastMovement == null || lastMovement.getType() != MovementType.BORROWING) {
        throw new RuntimeException("This lector has not borrowed this book");
            }
                    book.setAvailableCount(book.getAvailableCount() + 1);
        book.setAvailable(true);
        }
```

---
