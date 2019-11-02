<template>
  <div class="field has-addons has-addons-centered">
    <p class="control">
      <span class="select">
        <select v-model="category">
          <option value=''>All categories</option>
          <option v-for="(value, key) in categories" :key="key" :value="key">{{value.name}}</option>
        </select>
      </span>
    </p>
    <p class="control is-expanded">
      <input v-model="searchQuery" class="input" type="text" placeholder="Ask a question">
    </p>
    <p class="control">
      <a class="button is-primary">Search</a>
    </p>
  </div>
</template>

<script>
import categories from '../model/categories';

export default {
  name: 'Home',
  props: {
    category: {
      type: String,
      default: ''
    },
    searchQuery: {
      type: String,
      default: ''
    }
  },
  data: () => ({
    categories
  })
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';
</style>
