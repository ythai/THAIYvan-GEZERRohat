import React, { useState } from 'react';

function CreateEvent() {
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [type, setType] = useState('');
    const [dateTime, setDateTime] = useState('');
    const [availableSlots, setAvailableSlots] = useState(0);
    const [isPaid, setIsPaid] = useState(false);
    const [price, setPrice] = useState(0);
    const [description, setDescription] = useState('');

    const handleCreateEvent = async (e) => {
        e.preventDefault();
        const response = await fetch('http://localhost:8080/api/events', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name,
                location,
                type,
                date_time: dateTime,
                available_slots: availableSlots,
                is_paid: isPaid,
                price,
                description,
            }),
        });

        if (response.ok) {
            alert('Événement créé avec succès!');
        } else {
            alert('Erreur lors de la création de l\'événement');
        }
    };

    return (
        <div className="container mt-5">
            <h2>Créer un Événement</h2>
            <form onSubmit={handleCreateEvent}>
                <div className="mb-3">
                    <label className="form-label">Nom de l'événement:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Lieu:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={location}
                        onChange={(e) => setLocation(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Type:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={type}
                        onChange={(e) => setType(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Date et Heure:</label>
                    <input
                        type="datetime-local"
                        className="form-control"
                        value={dateTime}
                        onChange={(e) => setDateTime(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Nombre de places:</label>
                    <input
                        type="number"
                        className="form-control"
                        value={availableSlots}
                        onChange={(e) => setAvailableSlots(e.target.value)}
                    />
                </div>
                <div className="mb-3 form-check">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        checked={isPaid}
                        onChange={(e) => setIsPaid(e.target.checked)}
                    />
                    <label className="form-check-label">Payant</label>
                </div>
                {isPaid && (
                    <div className="mb-3">
                        <label className="form-label">Prix:</label>
                        <input
                            type="number"
                            className="form-control"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        />
                    </div>
                )}
                <div className="mb-3">
                    <label className="form-label">Description:</label>
                    <textarea
                        className="form-control"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    ></textarea>
                </div>
                <button type="submit" className="btn btn-primary">Créer</button>
            </form>
        </div>
    );
}

export default CreateEvent;
