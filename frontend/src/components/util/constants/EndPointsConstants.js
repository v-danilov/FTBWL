export const END_POINTS = {
  GET_ALL: {
    GAME_SYSTEMS: '/dict/gameSystems',
    DICTS_BY_GAME_SYSTEM: '/dict/gameSystem/',
    FORMATS: '/formats/getAll',
    COUNTRIES: '/countries/getAll',
    CITIES: '/cities/getAll',
    PLACES: '/places/getAll',
    ORGANIZERS: '/organizers/getAll',
    STATUSES: '/statuses/getAll',
    EVENTS: '/events',
    FRACTIONS: '/fractions/getAll'
  },
  EVENT: {
    REG_USER: '/registrationRequest',
    START: '/event/start/',
    UPDATE_STATUS: '/event/statusUpdate',
    CREATE: '/event/create',
    // TODO replace parameters
    SAVE_PLAYERS: '/event/{id}/savePlayers'
  },
  AUTHORIZATION: {
    SIGN_UP: '/api/signUp',
    SIGN_IN: '/signIn',
    GET_CITIES_BY_PREFIX: '/api/getCitiesByPrefix'
  }
}
