export default class Tournament {
    constructor(idTour, nameTour, status, timeEnd, timeStart, type, description, title) {
        this.idTour = idTour;
        this.nameTour = nameTour;
        this.status = status;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;
        this.type = type;
        this.description = description;
        this.title = title;
    }
}
