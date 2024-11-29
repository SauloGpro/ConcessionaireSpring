# 🚗 **ConcessionaireSpring**  
_Back-end desarrollado con Spring Boot para la gestión de concesionarios: usuarios, marcas y vehículos._

## 🌟 **Descripción**
ConcessionaireSpring es una aplicación robusta construida con **Spring Boot**, diseñada para gestionar concesionarios. Ofrece autenticación basada en **JWT**, control de acceso por roles y APIs para manejar información de marcas y vehículos.

---

## ✨ **Características**

### 🔒 **Autenticación**
- Autenticación segura con **JSON Web Tokens (JWT)**.
- Registro de nuevos usuarios con contraseñas encriptadas.
- Control de acceso basado en roles:  
  - **CLIENT**: Usuarios con permisos básicos.
  - **VENDOR**: Permite administrar marcas y vehículos.

### 🏷️ **Gestión de Marcas (Brands)**
- Consultar información de marcas.
- Crear, actualizar y eliminar marcas (solo usuarios con rol **VENDOR**).

### 🚘 **Gestión de Vehículos (Cars)**
- Consultar información de vehículos.
- Crear, actualizar y eliminar vehículos (solo usuarios con rol **VENDOR**).

---

## 📚 **Instalación**

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/tuusuario/ConcessionaireSpring.git
   cd ConcessionaireSpring

2. **Configura la base de datos:**
- Asegúrate de tener configurada una base de datos (por ejemplo, MySQL o PostgreSQL).
- Actualiza el archivo application.properties con tus credenciales de base de datos.

3. **Construye y ejecuta la aplicación:**

bash
Copiar código
- mvn clean install
- mvn spring-boot:run

4. **Accede a la API:**
- La aplicación estará disponible en http://localhost:8080.

# 🚀 **Endpoints**

**🔑 Autenticación**
- POST /authenticate
Autentica un usuario y genera un token JWT.

Body de la solicitud:

json
Copiar código
{
  "email": "user@example.com",
  "password": "password123"
}
Respuesta exitosa:

json
Copiar código
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
- POST /register
Registra un nuevo usuario.

Body de la solicitud:

json
Copiar código
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "password123"
}
Respuesta exitosa:
User registered successfully

🏷️ Marcas (Brands)
- GET /brands/{id}
Obtiene información de una marca específica.
Respuesta exitosa:

json
Copiar código
{
  "id": 1,
  "name": "BrandName",
  "description": "Brand description"
}
- POST /brands
Crea una nueva marca (solo rol VENDOR).
Body de la solicitud:

json
Copiar código
{
  "name": "BrandName",
  "description": "Brand description"
}
- PUT /brands/{id}
Actualiza una marca existente (solo rol VENDOR).

- DELETE /brands/{id}
Elimina una marca existente (solo rol VENDOR).

🚘 Vehículos (Cars)
- GET /cars/{id}
Obtiene información de un vehículo específico.
Respuesta exitosa:

json
Copiar código
{
  "id": 1,
  "brandId": 1,
  "model": "CarModel",
  "year": 2023,
  "price": 25000
}
- POST /cars
Crea un nuevo vehículo (solo rol VENDOR).
Body de la solicitud:

json
Copiar código
{
  "brandId": 1,
  "model": "CarModel",
  "year": 2023,
  "price": 25000
}
- PUT /cars/{id}
Actualiza un vehículo existente (solo rol VENDOR).

- DELETE /cars/{id}
Elimina un vehículo existente (solo rol VENDOR).

⚙️ Mejoras Futuras
Implementar búsquedas avanzadas para marcas y vehículos.
Añadir soporte para imágenes de vehículos.
Introducir notificaciones para los usuarios sobre actualizaciones en sus datos.
Ampliar roles y permisos (por ejemplo, ADMIN).
🤝 Contribuciones
¡Las contribuciones son bienvenidas! Si tienes una idea para mejorar este proyecto, no dudes en hacer un fork y enviar un pull request.

📄 Licencia
Este proyecto está licenciado bajo la MIT License.

Gracias por revisar este proyecto! 🌟 Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue o contactarme.