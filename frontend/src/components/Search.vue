<template>
  <div>
    <nav class="navbar" role="navigation" aria-label="main navigation">
      <div class="container">
        <div class="navbar-brand">
          <router-link :to="{name: 'home'}" exact class="navbar-item navbar-item-logo">
            <font-awesome-icon icon="shapes" size="lg" class="icon-shapes"></font-awesome-icon>
            <font-awesome-icon icon="child" size="lg" class="icon-child"></font-awesome-icon>
            <div class="brand-name">eli5</div>
          </router-link>
        </div>
        <div class="navbar-item navbar-item-search">
          <SearchInput
            class="search-input"
            :searchQuery="searchQuery"
            :category="category"
            @submit="onSearchSubmit">
          </SearchInput>
        </div>
      </div>
    </nav>
    <div class="section">
      <div class="container">
        <section v-if="answers != null && answers.length > 0" class="results">
          <p class="results-summary">Found {{response.data.numberOfElements}}
            matches of {{response.data.totalElements}} total for: "{{searchQuery}}"
            <span v-if="category !== ''">in {{categories[category].name}}</span></p>
          <ol class="answers" >
            <li v-for="answer in answers" :key="answer.id">
              <p>
                <category-tag
                    :category="categories[answer.question.category] || categories.other">
                </category-tag>
                <small class="question">{{answer.question.text}}</small>
                <small class="karma">
                  ({{answer.question.karma}} points)
                  <a :href="answer.question.url" target="_blank" class="external-link">
                    <font-awesome-icon icon="external-link-alt"></font-awesome-icon>
                  </a>
                </small>
              </p>
              <p>
                <span class="text" v-html="answer.text"></span>
                <small class="karma">
                  ({{answer.karma}} points)
                  <a :href="answer.url" target="_blank" class="external-link">
                    <font-awesome-icon icon="external-link-alt"></font-awesome-icon>
                  </a>
                </small>
              </p>
            </li>
          </ol>
          <nav class="pagination is-centered" role="navigation" aria-label="pagination">
            <a class="pagination-previous"
                @click="onPreviousPageClick()"
                :disabled="data.first">Previous</a>
            <a class="pagination-next"
                @click="onNextPageClick()"
                :disabled="data.last">Next page</a>
            <ul class="pagination-list">
              <li>
                <a class="pagination-link"
                    @click="onFirstPageClick()"
                    :disabled="!canGoToFirstPage()">1</a>
                </li>
              <li><span class="pagination-ellipsis">&hellip;</span></li>
              <li><a class="pagination-link is-current" aria-current="page">{{page + 1}}</a></li>
              <li><span class="pagination-ellipsis">&hellip;</span></li>
              <li>
                <a class="pagination-link"
                  @click="onLastPageClick()"
                  :disabled="!canGoToLastPage()">{{data.totalPages}}</a>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
import service from '@/common/service';
import CategoryTag from './CategoryTag.vue';
import SearchInput from './SearchInput.vue';
import categories from '../model/categories';

export default {
  name: 'Search',
  props: {
    searchQuery: {
      type: String,
      default: ''
    },
    category: {
      type: String,
      default: '',
      validator: value => [...Object.keys(categories), ''].includes(value)
    },
    page: {
      type: Number,
      default: 0
    }
  },
  data: () => ({
    categories,
    response: null
  }),
  computed: {
    answers() {
      return this.response ? this.response.data.content : [];
    },
    data() {
      return this.response ? this.response.data : null;
    }
  },
  methods: {
    async fetchAnswers(searchQuery, category, page) {
      const results = await service.findAnswers(searchQuery || this.searchQuery,
        category || this.category,
        page || this.page || 0, 10);
      this.response = results;
    },
    async onSearchSubmit(q, c) {
      this.$router.push({ name: 'search', query: { q, c, page: 0 } })
        .catch(() => {
          this.fetchAnswers();
        });
    },
    async goToPage(page) {
      this.$router.push({ name: 'search', query: { q: this.searchQuery, c: this.category, page } })
        .catch(() => {
          this.fetchAnswers();
        });
    },
    onNextPageClick() {
      if (!this.data.last) this.goToPage(this.page + 1);
    },
    onPreviousPageClick() {
      if (!this.data.first) this.goToPage(this.page - 1);
    },
    canGoToFirstPage() {
      return !this.data.first && this.data.totalPages > 1;
    },
    canGoToLastPage() {
      return !this.data.last && this.data.totalPages > 1;
    },
    onFirstPageClick() {
      if (this.canGoToFirstPage()) this.goToPage(0);
    },
    onLastPageClick() {
      if (!this.canGoToLastPage()) this.goToPage(this.data.totalPages - 1);
    }
  },
  watch: {
    $route() {
      this.fetchAnswers();
    }
  },
  async created() {
    await this.fetchAnswers();
  },
  components: {
    CategoryTag,
    SearchInput
  },
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.results-summary {
  margin-bottom: 1rem;
}

.text {
  white-space: pre-wrap;
}

.answers {
  li {
    list-style-type: none;
    padding: 1rem;
    border-radius: 4px;
    background-color: white;
    box-shadow: 0px 0px 18px -8px rgba(0,0,0,0.75);

    &:not(:last-child) {
      margin-bottom: 1rem;
    }

    .question {
      margin-left: .5rem;
    }

    p:first-of-type {
      margin-bottom: .5rem;
    }
  }
}

.pagination {
  border-radius: 4px;
  background-color: white;
  box-shadow: 0px 0px 18px -8px rgba(0,0,0,0.2);
  padding: 6px;
  margin-top: 20px;
}

.external-link {
  margin-left: 3px;
}

.karma {
  font-size: .8em;
}

/* navbar */

@media (min-width: $tablet) and (max-width: $desktop) {
  .icon-shapes {
    margin-left: 15px;
  }
}

.icon-child {
  margin-left: 5px;
}

.brand-name {
  margin-left: 5px;
  font-size: 1.5em;
  font-weight: 500;
}

.navbar-item-logo {
  margin: auto;
  flex-grow: 0;
}

.navbar-item-search {
  flex-grow: 1;
}

.search-input {
  width: 100%;
}
</style>
