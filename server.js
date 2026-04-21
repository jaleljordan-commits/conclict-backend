const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors());
app.use(express.json());

app.get('/api/conflicts', (req, res) => {
  res.json([{ id: 1, name: 'Ejemplo de conflicto' }]);
});

app.listen(8080, () => {
  console.log('Backend corriendo en http://localhost:8080');
});