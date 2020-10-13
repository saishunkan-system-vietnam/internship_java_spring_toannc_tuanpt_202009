<template>
  <v-card>
    <v-tabs v-model="tab" class="p-1" fixed-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab>Các giải đấu</v-tab>
      <v-tab>Đang diễn ra</v-tab>
      <v-tab>Kết thúc</v-tab>
      <v-tab>Sắp diễn ra</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab" style="margin-top: 50px">
      <v-tab-item>
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel v-for="(tournament, i) in tournaments" :key="i">
            <v-expansion-panel-header class="header"
              ><h5>{{ tournament.nameTour }}</h5></v-expansion-panel-header
            >
            <v-expansion-panel-content>
              <v-list dense>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, index) in tournament.schedule"
                    :key="index"
                  >
                    <v-list-item-content>
                      <v-row
                        v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                        v-model="open"
                      >
                        <v-col cols="12" sm="4">Đang diễn ra</v-col>
                        <v-col cols="12" sm="8">
                          <v-row>
                            <v-col> {{ item.team[0].nameTeam }}</v-col>
                            <v-col
                              >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                              {{
                                item.status == 2 ? item.scoreTeam2 : "?"
                              }}</v-col
                            >
                            <v-col>{{ item.team[1].nameTeam }}</v-col>
                          </v-row></v-col
                        >
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels></v-tab-item
      >
      <v-tab-item>
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel
            v-for="(tournament, i) in tournamentsLive"
            :key="i"
          >
            <v-expansion-panel-header class="header"
              ><h5>{{ tournament.nameTour }}</h5></v-expansion-panel-header
            >
            <v-expansion-panel-content>
              <v-list dense>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, index) in tournament.schedule"
                    :key="index"
                  >
                    <v-list-item-content>
                      <v-row
                        v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                        v-model="open"
                      >
                        <v-col cols="12" sm="4">Đang diễn ra</v-col>
                        <v-col cols="12" sm="8">
                          <v-row>
                            <v-col> {{ item.team[0].nameTeam }}</v-col>
                            <v-col
                              >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                              {{
                                item.status == 2 ? item.scoreTeam2 : "?"
                              }}</v-col
                            >
                            <v-col>{{ item.team[1].nameTeam }}</v-col>
                          </v-row></v-col
                        >
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels></v-tab-item
      >
      <v-tab-item>
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel v-for="(tournament, i) in tournamentsEnd" :key="i">
            <v-expansion-panel-header class="header"
              ><h5>{{ tournament.nameTour }}</h5></v-expansion-panel-header
            >
            <v-expansion-panel-content>
              <v-list dense>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, index) in tournament.schedule"
                    :key="index"
                  >
                    <v-list-item-content>
                      <v-row
                        v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                        v-model="open"
                      >
                        <v-col cols="12" sm="4">Kết thúc</v-col>
                        <v-col cols="12" sm="8">
                          <v-row>
                            <v-col> {{ item.team[0].nameTeam }}</v-col>
                            <v-col
                              >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                              {{
                                item.status == 2 ? item.scoreTeam2 : "?"
                              }}</v-col
                            >
                            <v-col>{{ item.team[1].nameTeam }}</v-col>
                          </v-row></v-col
                        >
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels></v-tab-item
      >
      <v-tab-item>
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel
            v-for="(tournament, i) in tournamentsUpcoming"
            :key="i"
          >
            <v-expansion-panel-header class="header"
              ><h5>{{ tournament.nameTour }}</h5></v-expansion-panel-header
            >
            <v-expansion-panel-content>
              <v-list dense>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, index) in tournament.schedule"
                    :key="index"
                  >
                    <v-list-item-content>
                      <v-row
                        v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                        v-model="open"
                      >
                        <v-col cols="12" sm="4">Sắp diễn ra</v-col>
                        <v-col cols="12" sm="8">
                          <v-row>
                            <v-col> {{ item.team[0].nameTeam }}</v-col>
                            <v-col
                              >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                              {{
                                item.status == 2 ? item.scoreTeam2 : "?"
                              }}</v-col
                            >
                            <v-col>{{ item.team[1].nameTeam }}</v-col>
                          </v-row></v-col
                        >
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels></v-tab-item
      >
    </v-tabs-items>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      tab: null,
      tournaments: "",
      panel: [0, 1, 2, 3, 4, 5],
      tournamentsUpcoming:'',
      tournamentsLive:'',
      tournamentsEnd:'',
      open: false,

    };
  },
  created() {
    this.$store
      .dispatch("tournament/getByType", "TableTennis")
      .then((response) => {
        this.tournaments = response.data;
      });
    this.$store
      .dispatch("tournament/getByStatus", { status: "0", type: "TableTennis" })
      .then((response) => {
        this.tournamentsUpcoming = response.data;
      });
    this.$store
      .dispatch("tournament/getByStatus", { status: "1", type: "TableTennis" })
      .then((response) => {
        this.tournamentsLive = response.data;
      });
    this.$store
      .dispatch("tournament/getByStatus", { status: "2", type: "TableTennis" })
      .then((response) => {
        this.tournamentsEnd = response.data;
      });
  },
   methods: {
    detail(data) {
      this.detailScore = data;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/soccer/detail/" + data.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>
<style>
.header {
  color: red;
  font-weight: bold;
}
</style>