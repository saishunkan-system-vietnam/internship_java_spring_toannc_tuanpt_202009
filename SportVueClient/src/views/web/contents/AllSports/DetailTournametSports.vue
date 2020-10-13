<template>
  <div>
    <v-container>
      <v-card>
        <h3>Giải đấu :{{ data.nameTour }}</h3>

        <v-tabs v-model="tab">
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1"> Tóm tắt </v-tab>

          <v-tab href="#tab-2"> Các trận đấu </v-tab>

          <v-tab href="#tab-3"> </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :value="'tab-1'">
            <v-card flat>
              <v-card-text>
                <h5 style="color: red">Các đội tham gia</h5>
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">Tên</th>
                        <th class="text-left">Logo</th>
                        <th class="text-left">Số thành viên</th>
                        <th class="text-left">Description</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="item in data.team" :key="item.name">
                        <td>{{ item.nameTeam }}</td>
                        <td>
                          <v-avatar>
                            <img :src="item.logo" alt="John" />
                          </v-avatar>
                        </td>
                        <td>{{ item.profile.length }}</td>
                        <td>{{ item.description }}</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-2'">
            <v-card flat>
              <v-card-text>
                <v-list dense>
                  <v-list-item-group
                    v-for="(item, index) in data.schedule"
                    color="primary"
                    :key="index"
                  >
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title>
                          <v-row
                            v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                            @click="detail(item)"
                            v-model="open"
                          >
                            <v-col cols="12" sm="4">Sắp diễn ra</v-col>
                            <v-col cols="12" sm="6">
                              <v-row>
                                <v-col> {{ item.team[0].nameTeam }}</v-col>
                                <v-col
                                  >{{
                                    item.status == 2 ? item.scoreTeam1 : "?"
                                  }}-
                                  {{
                                    item.status == 2 ? item.scoreTeam2 : "?"
                                  }}</v-col
                                >
                                <v-col>{{ item.team[1].nameTeam }}</v-col>
                              </v-row>
                            </v-col>
                            <v-col></v-col>
                          </v-row>
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
              </v-card-text>
            </v-card>
          </v-tab-item>
        
        </v-tabs-items>
      </v-card>
    </v-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: "",
      tab: null,
      open:false
    };
  },
  created() {
    this.getdata();
  },
  methods: {
    getdata() {
      this.$store
        .dispatch("tournament/getById", this.$route.params.id)
        .then((response) => {
          this.data = response.data;
        });
    },
      detail(data) {
      this.detailScore = data;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/detail/" + data.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>