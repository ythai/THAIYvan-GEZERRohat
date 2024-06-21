import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';
import { Container, TextField, Button, Typography, Box, Alert, FormControlLabel, Checkbox } from '@mui/material';

const CreateParty = () => {
  const [form, setForm] = useState({
    name: '',
    location: '',
    type: '',
    date: '',
    remainingPlaces: 0,
    isPaid: false,
    price: 0.0,
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({
      ...form,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/events', form);
      setMessage('Événement créé avec succès !');
      setTimeout(() => navigate('/dashboard'), 2000);
    } catch (error) {
      setMessage('Échec de la création de l\'événement. Veuillez réessayer.');
      console.error(error.response ? error.response.data : error.message);
    }
  };

  return (
    <Container>
      <Box mt={5}>
        <Typography variant="h4" component="h1" gutterBottom>
          Créer une soirée
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField fullWidth margin="normal" name="name" label="Nom de la soirée" onChange={handleChange} />
          <TextField fullWidth margin="normal" name="location" label="Lieu" onChange={handleChange} />
          <TextField fullWidth margin="normal" name="type" label="Type" onChange={handleChange} />
          <TextField fullWidth margin="normal" type="datetime-local" name="date" label="Date et Heure" onChange={handleChange} InputLabelProps={{ shrink: true }} />
          <TextField fullWidth margin="normal" type="number" name="remainingPlaces" label="Places restantes" onChange={handleChange} />
          <FormControlLabel
            control={<Checkbox name="isPaid" checked={form.isPaid} onChange={handleChange} />}
            label="Payant"
          />
          {form.isPaid && <TextField fullWidth margin="normal" type="number" name="price" label="Prix" onChange={handleChange} />}
          <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: 20 }}>
            Créer
          </Button>
        </form>
        {message && (
          <Alert severity={message.includes('succès') ? 'success' : 'error'} style={{ marginTop: 20 }}>
            {message}
          </Alert>
        )}
      </Box>
    </Container>
  );
};

export default CreateParty;
