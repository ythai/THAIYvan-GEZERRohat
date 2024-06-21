import React, { useEffect, useState } from 'react';
import api from '../api';

const Events = () => {
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
    <div>
      <h1>Événements</h1>
      <ul>
        {events.map(event => (
          <li key={event.id}>
            <h2>{event.name}</h2>
            <p>{event.location}</p>
            <p>{event.type}</p>
            <p>{new Date(event.date).toLocaleString()}</p>
            <p>{event.remainingPlaces} places restantes</p>
            <p>{event.isPaid ? `Payant: ${event.price}€` : 'Gratuit'}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Events;
