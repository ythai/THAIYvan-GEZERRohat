import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function EventDetails() {
    const { id } = useParams();
    const [event, setEvent] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/${id}`)
            .then((response) => response.json())
            .then((data) => setEvent(data));
    }, [id]);

    if (!event) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container mt-5">
            <h2>{event.name}</h2>
            <p>Lieu : {event.location}</p>
            <p>Type : {event.type}</p>
            <p>Date et Heure : {event.date_time}</p>
            <p>Nombre de places restantes : {event.available_slots}</p>
            <p>{event.is_paid ? `Prix : ${event.price} €` : 'Gratuit'}</p>
            <p>Description : {event.description}</p>
        </div>
    );
}

export default EventDetails;
