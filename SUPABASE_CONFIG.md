# Configuración de Supabase para Conflict Tracker

## Pasos para conectar tu proyecto a Supabase

### 1. Actualizar archivo `.env`
- Abre el archivo `.env` en la carpeta `demo/`
- Reemplaza `REPLACE_WITH_YOUR_SUPABASE_PASSWORD` con tu contraseña de Supabase
- **Importante**: Este archivo contiene información sensible, no lo compartas ni lo subas a Git

### 2. Arquitectura cambios realizados
✅ **application.properties** actualizado:
- Driver cambiado a `org.postgresql.Driver`
- Dialecto de Hibernate cambiadoa `PostgreSQL10Dialect`
- URL configurada a tu instancia de Supabase
- DDL-auto configurado a `create` (creará tablas automáticamente en primer inicio)

✅ **Dependencies**:
- PostgreSQL JDBC driver ya está en `pom.xml`

### 3. Variables de Entorno
Las siguientes variables pueden leerse desde variables de sistema:
- `DATABASE_URL`: URL de conexión (ya configurada por defecto a Supabase)
- `DB_USERNAME`: Usuario (por defecto: postgres)
- `DB_PASSWORD`: Contraseña (debe ser configurada)
- `DDL_AUTO`: Estrategia de creación de tablas (create, update, validate, none)

### 4. Ejecutar la aplicación

#### Opción A: Con variables de entorno del sistema
```bash
cd demo
export DB_PASSWORD="tu_contraseña_supabase"
mvn spring-boot:run
```

#### Opción B: Con arquivo .env (requiere plugin)
Si deseas usar el archivo .env, agrega esta dependencia al pom.xml:
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>java-dotenv</artifactId>
    <version>5.3.1</version>
</dependency>
```

Luego crea un archivo `DotenvConfig.java`:
```java
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
```

### 5. Agregar .env a .gitignore
Asegúrate de que `.env` esté en tu `.gitignore`:
```
.env
*.log
target/
```

### 6. Credenciales de Supabase
- **Host**: db.nsghtrmtwtfldyernjvb.supabase.co
- **Puerto**: 5432
- **Base de datos**: postgres
- **Usuario**: postgres
- **Contraseña**: [obtén de tu panel de Supabase]

### 7. Pruebas de conexión
Una vez ejecutada la aplicación, verifica que:
- ✅ Las tablas se crean automáticamente (country, conflict, faction, event, etc.)
- ✅ No hay errores de conexión en los logs
- ✅ La API está respondiendo en http://localhost:8080

### Esquema de tablas creadas automáticamente:
- `country` - Países
- `conflict` - Conflictos
- `faction` - Facciones
- `event` - Eventos
- `conflict_country` - Relación conflicto-país
- `faction_country_support` - Relación facción-país para soporte

## Notas importantes:
- La contraseña NO debe estar en el código fuente
- Usa variables de entorno en producción
- El DDL-auto está en `create` inicialmente (crea tablas nuevas)
- Cámbialo a `update` después de la primera ejecución para preservar datos
- Ten cuidado con `drop` ya que eliminará todos los datos

