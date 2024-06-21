import React, { useEffect, useState } from 'react';
import api from '../api';
import { Link } from 'react-router-dom';
import { Container, Typography, Box, Grid, Card, CardContent, CardActions, Button } from '@mui/material';

const Dashboard = () => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await api.get('/events');
        setEvents(response.data);
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    };

    fetchEvents();
  }, []);

  return (
    <Container>
      <Box textAlign="center" mt={5}>
        <Typography variant="h4" component="h1" gutterBottom>
          Dashboard
        </Typography>
        <Button variant="contained" color="primary" component={Link} to="/create-party">
          Create Party
        </Button>
      </Box>
      <Grid container spacing={4} mt={3}>
        {events.map(event => (
          <Grid item key={event.id} xs={12} sm={6} md={4}>
            <Card>
              <CardContent>
                <Typography variant="h5" component="h2">
                  {event.name}
                </Typography>
                <Typography color="textSecondary">
                  {event.location}
                </Typography>
                <Typography>
                  {event.type}
                </Typography>
                <Typography>
                  {new Date(event.date).toLocaleString()}
                </Typography>
                <Typography>
                  {event.remainingPlaces} places restantes
                </Typography>
                <Typography>
                  {event.isPaid ? `Payant: ${event.price}€` : 'Gratuit'}
                </Typography>
              </CardContent>
              <CardActions>
                <Button size="small" color="primary" component={Link} to={`/party/${event.id}`}>
                  Voir les détails
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default Dashboard;
