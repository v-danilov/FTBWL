export const END_POINTS = {
  GET_ALL: {
    FORMATS: '/formats/getAll',
    COUNTRIES: '/countries/getAll',
    CITIES: '/cities/getAll',
    PLACES: '/places/getAll',
    ORGANIZERS: '/organizers/getAll',
    STATUSES: '/statuses/getAll',
    TOURNAMENTS: '/tournaments/getAll',
    FRACTIONS: '/fractions/getAll'
  },
  TOURNAMENT: {
    REG_USER: '/tournament/regUser',
    START: '/tournament/start/'
  },
  AUTHORIZATION: {
    SIGN_UP: '/api/signUp',
    SIGN_IN: '/signIn',
    GET_CITIES_BY_PREFIX: '/api/getCitiesByPrefix'
  }
}
