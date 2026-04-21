# 🚀 Despliegue en Railway - Guía Completa

## Problemas Comunes Solucionados

### ❌ Variables de Entorno que Fallaban
- **Problema**: Railway no reconocía algunas variables con valores por defecto
- **Solución**: Simplificadas las variables y removidos valores por defecto problemáticos

### ❌ Configuración de Base de Datos
- **Problema**: Configuración compleja causaba errores de conexión
- **Solución**: Configuración simplificada y optimizada para PostgreSQL

### ❌ Perfil de Spring Boot
- **Problema**: No se activaba el perfil de producción
- **Solución**: Archivo `application-production.properties` creado

## ✅ Variables de Entorno para Railway (COPIA Y PEGA)

```bash
DATABASE_URL=jdbc:postgresql://db.nsghtrmtwtfldyernjvb.supabase.co:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=TU_CONTRASEÑA_SUPABASE_AQUI
DDL_AUTO=update
PORT=8080
SPRING_PROFILES_ACTIVE=production
```

## 📋 Checklist para Despliegue Exitoso

### 1. Variables de Entorno en Railway
Ve a tu proyecto en Railway → Variables y configura:

| Variable | Valor | Requerido |
|----------|-------|-----------|
| `DATABASE_URL` | `jdbc:postgresql://db.nsghtrmtwtfldyernjvb.supabase.co:5432/postgres` | ✅ |
| `DB_USERNAME` | `postgres` | ✅ |
| `DB_PASSWORD` | `TU_CONTRASEÑA_SUPABASE_AQUI` | ✅ |
| `DDL_AUTO` | `update` | ✅ |
| `PORT` | `8080` | ✅ |
| `SPRING_PROFILES_ACTIVE` | `production` | ✅ |

### 2. Comando de Inicio
Railway debería detectar automáticamente el comando de Maven, pero si necesitas especificarlo:

```bash
./mvnw clean package -DskipTests && java -Dserver.port=$PORT -jar target/conflict-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=production
```

### 3. Verificación del Despliegue

Después del despliegue, verifica:

1. **Logs de Railway**: Deberías ver algo como:
   ```
   Starting ConflictTrackerApplication v0.0.1-SNAPSHOT using Java 17
   The following profiles are active: production
   HikariPool-1 - Starting...
   HikariPool-1 - Added connection...
   Started ConflictTrackerApplication in 15.123 seconds
   ```

2. **Endpoint de Health**: `https://tu-app.railway.app/actuator/health`
   - Debería retornar: `{"status":"UP"}`

3. **API Endpoints**: Prueba tus endpoints principales:
   - `GET https://tu-app.railway.app/v1/conflicts`

## 🔧 Configuración Optimizada para Railway

### Pool de Conexiones
- **Máximo pool size**: 10 conexiones
- **Mínimo idle**: 5 conexiones
- **Timeout de conexión**: 20 segundos

### Logging
- **Root level**: INFO
- **Aplicación**: INFO
- **Hibernate**: ERROR (menos verboso)

### JPA
- **DDL Auto**: `update` (mantiene tablas existentes)
- **Show SQL**: `false` (menos ruido en logs)

## 🚨 Solución de Problemas

### Error: "Failed to determine a suitable driver class"
```
Solución: Asegúrate de que DATABASE_URL esté configurada correctamente
```

### Error: "Connection refused"
```
Solución: Verifica que DB_PASSWORD sea correcta y que Supabase permita conexiones externas
```

### Error: "Port already in use"
```
Solución: Railway asigna automáticamente el puerto, no modifiques la variable PORT
```

### Error: "Application failed to start"
```
Solución: Revisa los logs completos en Railway para ver el error específico
```

## 📁 Archivos Modificados/Creados

- ✅ `application.properties` - Simplificado para Railway
- ✅ `application-production.properties` - Configuración de producción
- ✅ `railway.json` - Configuración específica de Railway
- ✅ `.env.railway` - Variables de entorno listas para copiar

## 🎯 Próximos Pasos

1. **Configura las variables** en Railway
2. **Haz deploy** desde GitHub
3. **Verifica los logs** para confirmar que funciona
4. **Prueba los endpoints** de la API
5. **Despliega el frontend** apuntando a la URL de Railway

¡Tu aplicación debería desplegarse correctamente ahora! 🎉