<template>
    <div class="d-flex justify-content-center"  style="min-height: 600px;" v-if="newstxs">
      <div class="card d-flex mt-1" style="width: 95%;">
        <div class="card-body">
          <!--Table-->
          <table class="table table-hover table-forum text-center">
            <!--Table head-->
            <thead>
              <tr>
                <th></th>
                <th class="text-primary h3">Tuyển sinh đào tạo từ xa</th>
                <th></th>
              </tr>
            </thead>
  
            <tbody>
              <tr v-for="newstx in newstxs" :key="newstx.id">
                <td scope="row" class="text-nowrap" style="width: 150px">
                  <router-link
                    :to="{ name: 'newsDetailCl', params: { id: newstx.id } }"
                  >
                    <img :src="newstx.image" :alt="newstx.title" class="w-100" />
                  </router-link>
                </td>
                <td class="text-start">
                  <router-link
                    :to="{ name: 'newsDetailCl', params: { id: newstx.id } }"
                    class="nav-link"
                  >
                    <div class="h5 text-success" style="margin: 0">
                      {{ newstx.title }}
                    </div>
                    <div class="my-2 mt-1">
                      <div style="font-size: large">
                        <span> {{ newstx.content }} </span>
                      </div>
                      <span>Ngày cập nhật: {{ newstx.ngaycapnhat }}</span>
                    </div>
                  </router-link>
                </td>
                <td>
                  <router-link
                    :to="{ name: 'newsDetailCl', params: { id: newstx.id } }"
                    class="nav-link"
                  >
                    <a href="#" class="text-dark">
                      <i class="fas fa-comments ml-1"></i>
                    </a>
                  </router-link>
                </td>
              </tr>
            </tbody>
          </table>
  
          <!--Bottom Table UI-->
          <div class="d-flex justify-content-center">
            <!--Pagination -->
            <nav class="my-2 pt-2">
              <ul class="pagination pagination-circle pg-info mb-0">
                <!--First-->
                <li class="page-item clearfix d-none d-md-block">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    @click="setCurrentPage(1)"
                  >
                    First
                  </a>
                </li>
                <!--Arrow left-->
                <li class="page-item">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    aria-label="Previous"
                    @click="setCurrentPage(currentPage - 1)"
                  >
                    <span aria-hidden="true">«</span>
                    <span class="sr-only"> Previous </span>
                  </a>
                </li>
                <!--Numbers-->
                <li v-for="page in itemsPerPage" :key="page" class="page-item">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    @click="setCurrentPage(page)"
                    >{{ page }}</a
                  >
                </li>
                <li class="page-item">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    aria-label="Next"
                    @click="setCurrentPage(currentPage + 1)"
                  >
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Next</span>
                  </a>
                </li>
                <li class="page-item clearfix d-none d-md-block">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    @click="setCurrentPage(3)"
                  >
                    Last
                  </a>
                </li>
              </ul>
            </nav>
            <!--/Pagination -->
          </div>
        </div>
      </div>
    </div>
    <div v-else class="text-center bg-info h2 pb-2" style="margin: 0">
      Không có tin nào!!
    </div>
  </template>
    <script>
  import Apis, { endpoints } from "@/configs/Apis";
  
  export default {
    name: "ListNewsTX",
    data() {
      return {
        newstxs: null,
        currentPage: 0,
        itemsPerPage: 0,
        searchQuery: "", // Thêm dữ liệu tìm kiếm
      };
    },
    mounted() {
      this.searchQuery = this.$route.query.search || "";
      console.log(this.searchQuery);
      this.fetchPageTX();
      this.fetchDataTX(1);
    },
    methods: {
      async fetchDataTX(page) {
        if (page < 1) page = 1;
        try {
          let location = endpoints["NewsTX"];
          if (this.searchQuery !== "") {
            location = `${location}?search=${this.searchQuery}`;
          } else if (this.searchQuery === "") {
            location = `${location}?page=${page}`;
          }
          let res = await Apis.get(location);
          this.newstxs = res.data;
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      },
      async fetchPageTX() {
        try {
          const response = await Apis.get(endpoints["PageTX"]);
          this.itemsPerPage = response.data;
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      },
      setCurrentPage(page) {
        this.currentPage = page;
        this.searchQuery = "";
        this.fetchDataTX(page);
      },
    },
    watch: {
      "$route.query.search": {
        handler(newSearchQuery) {
          this.searchQuery = newSearchQuery || "";
          this.fetchDataTX(1);
        },
        // immediate: true, // Thực hiện ngay khi component được khởi tạo
      },
    },
  };
  </script>