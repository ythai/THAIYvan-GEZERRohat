import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';
import { Container, TextField, Button, Typography, Box, Alert } from '@mui/material';

const Login = () => {
  const [form, setForm] = useState({
    email: '',
    password: ''
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/users/login', null, {
        params: {
          username: form.email,
          password: form.password
        }
      });
      localStorage.setItem('token', response.data.id_token);
      setMessage('Login successful');
      setTimeout(() => navigate('/dashboard'), 2000);
    } catch (error) {
      setMessage('Login failed. Please try again.');
      console.error(error.response ? error.response.data : error.message);
    }
  };

  return (
    <Container>
      <Box mt={5}>
        <Typography variant="h4" component="h1" gutterBottom>
          Se connecter
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField fullWidth margin="normal" type="email" name="email" label="Email" onChange={handleChange} />
          <TextField fullWidth margin="normal" type="password" name="password" label="Mot de passe" onChange={handleChange} />
          <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: 20 }}>
            Se connecter
          </Button>
        </form>
        {message && (
          <Alert severity={message.includes('successful') ? 'success' : 'error'} style={{ marginTop: 20 }}>
            {message}
          </Alert>
        )}
      </Box>
    </Container>
  );
};

export default Login;
