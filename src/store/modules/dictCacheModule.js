import {HTTPResponseStatusConstants} from '../../components/util/constants/CommonConstants'
import axios from 'axios'
/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  gameSystems: localStorage.getItem('gameSystems') || [],
  fractions: localStorage.getItem('fractions') || [],
  formats: localStorage.getItem('formats') || [],
  countries: localStorage.getItem('countries') || [],
  cities: localStorage.getItem('cities') || [],
  places: localStorage.getItem('places') || [],
  organizers: localStorage.getItem('organizers') || [],
  statuses: localStorage.getItem('statuses') || [],
  dictsLoaded: localStorage.getItem('dictsLoaded') || false
}
const getters = {
  cachedGameSystems: state => state.gameSystems,
  cachedFactions: state => state.factions,
  cachedFormats: state => state.formats,
  cachedCountries: state => state.countries,
  cachedCities: state => state.cities,
  cachedPlaces: state => state.places,
  cachedOrganizers: state => state.organizers,
  cachedStatuses: state => state.statuses,
  cachedRulePacks: state => state.rulePacks,
  dictsLoadedFlag: state => state.dictsLoaded
}
// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_GAME_SYSTEMS: (state, payload) => {
    state.gameSystems = payload
  },
  SET_FACTIONS: (state, payload) => {
    state.factions = payload
  },
  SET_FORMATS: (state, payload) => {
    state.formats = payload
  },
  SET_COUNTRIES: (state, payload) => {
    state.countries = payload
  },
  SET_CITIES: (state, payload) => {
    state.cities = payload
  },
  SET_PLACES: (state, payload) => {
    state.places = payload
  },
  SET_ORGANIZERS: (state, payload) => {
    state.organizers = payload
  },
  SET_STATUSES: (state, payload) => {
    state.statuses = payload
  },
  SET_RULEPACKS: (state, payload) => {
    state.rulePacks = payload
  },
  SET_DISCTS_LOADED_FLAG: (state, payload) => {
    state.dictsLoaded = payload
  }
}
// this.$store.dispatch("SET_USER",user) asynchronous

const actions = {
  INIT_DICTS: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_PLACES', response.data.places)
          commitAsMap('SET_FACTIONS', 'systemName', context, response.data.factions)
          commitAsMap('SET_FORMATS', 'code', context, response.data.eventFormats)
          commitAsMap('SET_STATUSES', 'code', context, response.data.eventStatuses)
          commitAsMap('SET_RULEPACKS', 'code', context, response.data.rulePacks)
          context.commit('SET_DISCTS_LOADED_FLAG', true)
        }
      }).catch(reason => {
        console.log('Failed to cache dictionatries 🤷‍♂️' + reason)
      })
  },
  INIT_GAME_SYSTEMS: (context, url) => {
    return axios.get(url).then(response => {
      if (response.status === HTTPResponseStatusConstants.OK) {
        commitAsMap('SET_GAME_SYSTEMS', 'code', context, response.data)
        return response.data
      }
    }).catch(reason => {
      console.log(reason)
      console.log('Failed to cache game systems 🤷‍♂️')
    })
  },
  INIT_FACTIONS: (context, url) => {
    axios.get(url).then(response => {
      if (response.status === HTTPResponseStatusConstants.OK) {
        commitAsMap('SET_FACTIONS', 'systemName', context, response.data)
      }
    }).catch(reason => {
      console.log('Failed to cache fractions 🤷‍♂️')
    })
  },
  INIT_FORMATS: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          commitAsMap('SET_FORMATS', 'code', context, response.data)
        }
      }).catch(reason => {
        console.log('Failed to cache formats 🤷‍♂️')
      })
  },
  INIT_COUNTRIES: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_COUNTRIES', response.data.payload)
        }
      }).catch(reason => {
        console.log('Failed to cache countries 🤷‍♂️')
      })
  },

  INIT_CITIES: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_CITIES', response.data.payload)
        }
      }).catch(reason => {
        console.log('Failed to cache cities 🤷‍♂️')
      })
  },
  INIT_PLACES: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_PLACES', response.data.payload)
        }
      }).catch(reason => {
        console.log('Failed to cache places 🤷‍♂️')
      })
  },
  INIT_ORGANIZERS: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_ORGANIZERS', response.data.payload)
        }
      }).catch(reason => {
        console.log('Failed to cache organizers 🤷‍♂️')
      })
  },
  INIT_STATUSES: async (context, url) => {
    await axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          commitAsMap('SET_STATUSES', 'code', context, response.data)
        }
      }).catch(reason => {
        console.log('Failed to cache statuses 🤷‍♂️')
      })
  }
}
function commitAsMap (setter, keyField, context, payload) {
  const payloadAsMap = new Map()
  payload.forEach(e => payloadAsMap.set(e[keyField], e))
  context.commit(setter, payloadAsMap)
}

export default {
  state, getters, mutations, actions
}
