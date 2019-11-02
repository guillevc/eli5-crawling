import HTTP from './http';

// TODO
export default {
  signup(name, username, email, password) {
    return HTTP.post('/user', {
      name,
      username,
      email,
      password
    });
  },
  login(username, password) {
    return HTTP.post('/auth', { username, password });
  },
  get(username) {
    return HTTP.get(`/users/${username}`);
  },
  async isUniqueUsername(username) {
    try {
      await HTTP.get(`/users/${username}`);
      return false;
    } catch (err) {
      return err.response.statusCode !== '404';
    }
  },
  getUserProfile(username) {
    return HTTP.get(`/users/${username}/profile`);
  },
  searchPaginated(query, page, size) {
    console.log(`service searching user query=${query} page=${page} size=${size}`);
    return HTTP.get('/users/search-paginated', { params: { q: query, page, size } });
  },
  getUserSettings(username) {
    return HTTP.get(`/users/${username}/settings`);
  },
  updateUserSettings(username, settings) {
    return HTTP.put(`/users/${username}/settings`, settings);
  }
};
