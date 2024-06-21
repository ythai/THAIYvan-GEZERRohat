import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Typography, Button, Box } from '@mui/material';

const Home = () => {
  return (
    <Container>
      <Box textAlign="center" mt={5}>
        <Typography variant="h2" component="h1" gutterBottom>
          Welcome to the Party App
        </Typography>
        <Box mt={3}>
          <Button variant="contained" color="primary" component={Link} to="/signup" style={{ marginRight: 10 }}>
            Sign Up
          </Button>
          <Button variant="contained" color="secondary" component={Link} to="/login">
            Login
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default Home;
