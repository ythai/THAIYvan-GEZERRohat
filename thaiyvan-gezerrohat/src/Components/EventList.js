import React, { useEffect, useState } from 'react';

function EventList() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/events')
            .then((response) => response.json())
            .then((data) => setEvents(data));
    }, []);

    return (
        <div className="container mt-5">
            <h2>Liste des événements</h2>
            <div className="list-group">
                {events.map((event) => (
                    <a
                        key={event.id}
                        href={`/event/${event.id}`}
                        className="list-group-item list-group-item-action"
                    >
                        {event.name}
                    </a>
                ))}
            </div>
        </div>
    );
}

export default EventList;
