import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../api';
import { Container, Typography, Box, Paper, List, ListItem, ListItemText, CircularProgress } from '@mui/material';

const PartyDetails = () => {
  const { id } = useParams();
  const [event, setEvent] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchEvent = async () => {
      try {
        const response = await api.get(`/events/${id}`);
        setEvent(response.data);
      } catch (error) {
        console.error('Error fetching event:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchEvent();
  }, [id]);

  if (loading) {
    return (
      <Container>
        <Box mt={5} textAlign="center">
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  if (!event) {
    return (
      <Container>
        <Box mt={5} textAlign="center">
          <Typography variant="h6">Événement non trouvé</Typography>
        </Box>
      </Container>
    );
  }

  return (
    <Container>
      <Box mt={5}>
        <Typography variant="h4" component="h1" gutterBottom>
          {event.name}
        </Typography>
        <Paper elevation={3} style={{ padding: 20 }}>
          <Typography variant="h6">Détails</Typography>
          <Typography>Lieu: {event.location}</Typography>
          <Typography>Type: {event.type}</Typography>
          <Typography>Date et Heure: {new Date(event.date).toLocaleString()}</Typography>
          <Typography>Places restantes: {event.remainingPlaces}</Typography>
          <Typography>{event.isPaid ? `Prix: ${event.price}€` : 'Gratuit'}</Typography>
        </Paper>
        <Box mt={3}>
          <Typography variant="h6">Commentaires</Typography>
          <List>
            {event.comments && event.comments.length > 0 ? (
              event.comments.map((comment) => (
                <ListItem key={comment.id}>
                  <ListItemText primary={comment.text} />
                </ListItem>
              ))
            ) : (
              <Typography>Aucun commentaire</Typography>
            )}
          </List>
        </Box>
      </Box>
    </Container>
  );
};

export default PartyDetails;
