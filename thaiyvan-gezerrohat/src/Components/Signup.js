import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';
import { Container, TextField, Button, Typography, Box, Alert } from '@mui/material';

const Signup = () => {
  const [form, setForm] = useState({
    email: '',
    password: '',
    name: '',
    city: '',
    region: '',
    age: '',
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/users/register', form);
      setMessage('Inscription réussie ! Vous pouvez maintenant vous connecter.');
      setTimeout(() => {
        navigate('/login');
      }, 2000);
    } catch (error) {
      setMessage('Échec de l\'inscription. Veuillez réessayer.');
      console.error(error.response ? error.response.data : error.message);
    }
  };

  return (
    <Container>
      <Box mt={5}>
        <Typography variant="h4" component="h1" gutterBottom>
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField fullWidth margin="normal" type="email" name="email" label="Email" onChange={handleChange} />
          <TextField fullWidth margin="normal" type="password" name="password" label="Mot de passe" onChange={handleChange} />
          <TextField fullWidth margin="normal" name="name" label="Nom" onChange={handleChange} />
          <TextField fullWidth margin="normal" name="city" label="Ville" onChange={handleChange} />
          <TextField fullWidth margin="normal" name="region" label="Région" onChange={handleChange} />
          <TextField fullWidth margin="normal" type="number" name="age" label="Âge" onChange={handleChange} />
          <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: 20 }}>
            S'inscrire
          </Button>
        </form>
        {message && (
          <Alert severity={message.includes('réussie') ? 'success' : 'error'} style={{ marginTop: 20 }}>
            {message}
          </Alert>
        )}
      </Box>
    </Container>
  );
};

export default Signup;
